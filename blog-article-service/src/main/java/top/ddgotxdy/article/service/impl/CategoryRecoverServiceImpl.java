package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogCategoryService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@ArticleEventSelector(ArticleEvent.CATEGORY_RECOVER)
@Component
@Slf4j
public class CategoryRecoverServiceImpl extends AbstractArticleService {
    @Resource
    private BlogCategoryService blogCategoryService;
    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkIsAdmin(articleContext);
        if (!allCommonCheck) {
            return false;
        }
        // 2. 标签列表是否传过来
        if (Objects.isNull(articleContext.getCategoryIds())) {
            log.error("category ids is null");
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        List<Long> categoryIds = articleContext.getCategoryIds();
        boolean isOk = blogCategoryService.recoverBatchByIds(categoryIds);
        if (isOk) {
            articleContext.setCategoryIds(Collections.emptyList());
        }
    }
}
