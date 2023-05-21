package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogCategoryService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;

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
        // 2. 标签列表是否传过来
        if (Objects.isNull(articleContext.getCategoryIds())) {
            throw new BlogException(ResultCode.CATEGORY_RECOVERY_ERROR.getCode(), "分类id列表为空");
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
