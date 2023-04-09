package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogCategoryService;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description: 分类更新服务类
 */
@ArticleEventSelector(ArticleEvent.CATEGORY_UPDATE)
@Component
@Slf4j
public class CategoryUpdateServiceImpl extends AbstractArticleService {
    @Resource
    private BlogCategoryService blogCategoryService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        return false;
    }

    @Override
    protected void doExecute(ArticleContext articleContext) {

    }
}
