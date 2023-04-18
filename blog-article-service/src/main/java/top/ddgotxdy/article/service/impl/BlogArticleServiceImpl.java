package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.dal.entity.BlogArticle;
import top.ddgotxdy.dal.mapper.BlogArticleMapper;

import java.util.List;

/**
 * @author: ddgo
 * @description: 文章服务实现类
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Override
    public List<BlogArticle> getArticleByTagId(Long tagId) {
        LambdaQueryWrapper<BlogArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(BlogArticle::getArticleId, BlogArticle::getTagIds, BlogArticle::getIsDelete)
                .like(BlogArticle::getTagIds, tagId);
        return this.list(queryWrapper);
    }

    @Override
    public List<BlogArticle> getArticleByCategoryId(Long categoryId) {
        LambdaQueryWrapper<BlogArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(BlogArticle::getArticleId, BlogArticle::getArticleId, BlogArticle::getIsDelete)
                .eq(BlogArticle::getCategoryId, categoryId);
        return this.list(queryWrapper);
    }
}
