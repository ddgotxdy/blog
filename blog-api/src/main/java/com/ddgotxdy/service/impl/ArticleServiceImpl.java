package com.ddgotxdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddgotxdy.dos.Archives;
import com.ddgotxdy.entity.Article;
import com.ddgotxdy.entity.ArticleBody;
import com.ddgotxdy.entity.SysUser;
import com.ddgotxdy.mapper.ArticleBodyMapper;
import com.ddgotxdy.mapper.ArticleMapper;
import com.ddgotxdy.mapper.TagMapper;
import com.ddgotxdy.service.*;
import com.ddgotxdy.vo.*;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ddgo
 * @description: 文章服务实现类
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ArticleBodyMapper articleBodyMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ThreadService threadService;

    @Override
    public Result listArticle(PageParams pageParams) {
        // 分页查询 article 数据库表
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        // sql语句
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 按照置顶排序
        queryWrapper.orderByDesc(Article::getWeight);
        // 按照发布时间从大到小排序
        queryWrapper.orderByDesc(Article::getCreateDate);
        // 获取内容
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        // 封装成VO对象
        List<ArticleVO> articleVOList = copyList(records, true, false, true);
        return Result.success(articleVOList);
    }

    @Override
    public Result hotArticle(int limit) {
        // sql语句
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 按照浏览量排序
        queryWrapper.orderByDesc(Article::getViewCounts);
        // 选择文章的id和标题
        queryWrapper.select(Article::getId, Article::getTitle);
        // 最后添加 limit
        queryWrapper.last("limit " + limit);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articleList, false, false, false));
    }

    @Override
    public Result newArticles(int limit) {
        // sql语句
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 按照时间排序
        queryWrapper.orderByDesc(Article::getCreateDate);
        // 选择文章的id和标题
        queryWrapper.select(Article::getId, Article::getTitle);
        // 最后添加 limit
        queryWrapper.last("limit " + limit);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articleList, false, false, false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    @Override
    public Result findArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        ArticleVO articleVO = copy(article, true, true, true, true);
        threadService.updateViewCount(article);
        return Result.success(articleVO);
    }

    @Override
    public Result findArticleBodyById(Long id) {
        ArticleBody articleBody = articleBodyMapper.selectById(id);
        ArticleBodyVO articleBodyVO = new ArticleBodyVO();
        articleBodyVO.setContent(articleBody.getContent());
        return Result.success(articleBodyVO);
    }


    private List<ArticleVO> copyList(List<Article> records, boolean isAuthor, boolean isBody, boolean isTags) {
        List<ArticleVO> articleVoList = new ArrayList<>();
        for (Article article : records) {
            ArticleVO articleVO = copy(article, isAuthor, isBody, isTags, false);
            articleVoList.add(articleVO);
        }
        return articleVoList;
    }

    private ArticleVO copy(Article article, boolean isAuthor, boolean isBody, boolean isTags, boolean isCategory) {
        ArticleVO articleVO = new ArticleVO();
        // 把两个对象中相同字段进行赋值
        BeanUtils.copyProperties(article, articleVO);
        articleVO.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        // 是否显示标签
        if(isTags) {
            Long articleId = article.getId();
            List<TagVO> tagVOList = (List<TagVO>) tagService.findTagsByArticleId(articleId).getData();
            articleVO.setTags(tagVOList);
        }
        // 是否显示作者
        if(isAuthor) {
            Long authorId = article.getAuthorId();
            SysUser sysUser = (SysUser) sysUserService.findUserById(authorId).getData();
            articleVO.setAuthor(sysUser.getNickname());
        }
        // 是否显示body
        if(isBody) {
            Long bodyId = article.getBodyId();
            articleVO.setBody((ArticleBodyVO) findArticleBodyById(bodyId).getData());
        }
        // 是否显示分类
        if(isCategory) {
            Long categoryId = article.getCategoryId();
            articleVO.setCategory((CategoryVO) categoryService.findCategoryById(categoryId).getData());
        }

        return articleVO;
    }
}
