package top.ddgotxdy.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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

    @Override
    public boolean deleteBatchByIds(List<Long> articleIds) {
        LambdaUpdateWrapper<BlogArticle> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogArticle::getIsDelete, true)
                .in(BlogArticle::getArticleId, articleIds);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoveryBatchByIds(List<Long> articleIds) {
        LambdaUpdateWrapper<BlogArticle> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogArticle::getIsDelete, false)
                .in(BlogArticle::getArticleId, articleIds);
        return this.update(updateWrapper);
    }

    @Override
    public long getCount() {
        LambdaQueryWrapper<BlogArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogArticle::getIsDelete, false);
        return this.count(queryWrapper);
    }
}
