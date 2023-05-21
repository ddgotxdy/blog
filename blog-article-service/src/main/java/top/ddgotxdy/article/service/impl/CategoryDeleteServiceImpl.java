package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.article.service.BlogCategoryService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogArticle;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: ddgo
 * @description:
 */
@ArticleEventSelector(ArticleEvent.CATEGORY_DELETE)
@Component
@Slf4j
public class CategoryDeleteServiceImpl extends AbstractArticleService {
    @Resource
    private BlogCategoryService blogCategoryService;
    @Resource
    private BlogArticleService blogArticleService;
    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 2. 标签列表是否传过来
        if (Objects.isNull(articleContext.getCategoryIds())) {
            throw new BlogException(ResultCode.CATEGORY_DELETE_ERROR.getCode(), "分类id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        // 剩下没被处理的tag Id
        List<Long> categoryIdsRemain = new ArrayList<>();
        List<Long> categoryIds = articleContext.getCategoryIds();
        categoryIds.forEach(categoryId -> {
            List<BlogArticle> blogArticleList = blogArticleService.getArticleByCategoryId(categoryId);
            boolean allDelete = blogArticleList.stream().allMatch(BlogArticle::getIsDelete);
            // 存在被引用
            if (!allDelete) {
                categoryIdsRemain.add(categoryId);
                return;
            }
            // 更新文章
            List<BlogArticle> blogArticles = blogArticleList.stream()
                    .peek(blogArticle -> {
                        blogArticle.setIsDelete(null);
                        blogArticle.setCategoryId(0L);
                    })
                    .collect(Collectors.toList());
            blogArticleService.updateBatchById(blogArticles);
            // 更新标签
            blogCategoryService.deleteById(categoryId);
        });
        // 没有被删除
        articleContext.setCategoryIds(categoryIdsRemain);
    }
}
