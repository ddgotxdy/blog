package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.convert.Context2EntityConvert;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogCategoryService;
import top.ddgotxdy.dal.entity.BlogCategory;

import javax.annotation.Resource;
import java.util.Objects;

import static top.ddgotxdy.article.constant.ValidateConstant.MAX_CATEGORY_LENGTH;

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
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkIsAdmin(articleContext)
                && this.checkUniqueCategoryName(articleContext);
        if (!allCommonCheck) {
            return false;
        }
        // 2. 更新的分类的大小不超过最长的长度
        if (Objects.nonNull(articleContext.getCategoryName())
                && (StringUtils.length(articleContext.getCategoryName()) > MAX_CATEGORY_LENGTH
                || StringUtils.length(articleContext.getCategoryName()) < 1)) {
            // 先打error日志
            log.error("Over MAX_CATEGORY_LENGTH or Lower 1");
            return false;
        }
        // 3. 分类id必须传递
        if (Objects.isNull(articleContext.getCategoryId())) {
            log.error("category id is null");
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        BlogCategory blogCategory = Context2EntityConvert.articleContext2Category(articleContext);
        blogCategoryService.updateById(blogCategory);
    }
}
