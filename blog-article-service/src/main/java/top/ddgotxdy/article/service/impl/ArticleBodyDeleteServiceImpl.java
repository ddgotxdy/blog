package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogArticleService;
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
@ArticleEventSelector(ArticleEvent.ARTICLE_BODY_DELETE)
@Service
@Slf4j
public class ArticleBodyDeleteServiceImpl extends AbstractArticleService {
    @Resource
    private BlogArticleService blogArticleService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkIsAdmin(articleContext);
        if (!allCommonCheck) {
            throw new BlogException(ResultCode.ARTICLE_DELETE_ERROR.getCode(), "not admin");
        }
        // 2. 传递的列表
        if (Objects.isNull(articleContext.getArticleIds())) {
            throw new BlogException(ResultCode.ARTICLE_DELETE_ERROR.getCode(), "article ids is null");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        List<Long> articleIds = articleContext.getArticleIds();
        boolean ok = blogArticleService.deleteBatchByIds(articleIds);
        if (ok) {
            articleContext.setArticleIds(Collections.emptyList());
        }
    }
}
