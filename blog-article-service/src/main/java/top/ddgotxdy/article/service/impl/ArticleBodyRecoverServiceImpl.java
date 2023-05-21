package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogArticle;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@ArticleEventSelector(ArticleEvent.ARTICLE_BODY_RECOVER)
@Service
@Slf4j
public class ArticleBodyRecoverServiceImpl extends AbstractArticleService {
    @Resource
    private BlogArticleService blogArticleService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 2. 传递的列表
        if (Objects.isNull(articleContext.getArticleIds())) {
            throw new BlogException(ResultCode.ARTICLE_RECOVERY_ERROR.getCode(), "article ids is null");
        }
        return true;
    }

    @Override
    protected void doExecute(ArticleContext articleContext) {
        List<Long> articleIds = articleContext.getArticleIds();
        List<Long> articleIdsRemain = new ArrayList<>();
        // 判断当前article 是否能恢复
        articleIds.forEach(articleId -> {
            List<BlogArticle> blogArticleList = blogArticleService.listByIds(Collections.singletonList(articleId));
            if (CollectionUtils.isEmpty(blogArticleList)) {
                return;
            }
            BlogArticle blogArticle = blogArticleList.get(0);
            String tagIds = blogArticle.getTagIds();
            Long categoryId = blogArticle.getCategoryId();
            if (Objects.isNull(tagIds) || tagIds.equals("[]")) {
                articleIdsRemain.add(articleId);
                return;
            }
            if (Objects.isNull(categoryId) || categoryId == 0) {
                articleIdsRemain.add(articleId);
                return;
            }
            // 恢复
            boolean ok = blogArticleService.recoveryBatchByIds(Collections.singletonList(articleId));
            if (!ok) {
                articleIdsRemain.add(articleId);
            }
        });
        articleContext.setArticleIds(articleIdsRemain);
    }
}
