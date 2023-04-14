package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.convert.Context2EntityConvert;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.dal.entity.BlogArticle;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static top.ddgotxdy.article.constant.ValidateConstant.MAX_ARTICLE_CONTENT_LENGTH;

/**
 * @author: ddgo
 * @description: 文章更新服务
 */
@ArticleEventSelector(ArticleEvent.ARTICLE_BODY_UPDATE)
@Service
@Slf4j
public class ArticleBodyUpdateServiceImpl extends AbstractArticleService {
    @Resource
    private BlogArticleService blogArticleService;
    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkIsAdmin(articleContext);
        if (!allCommonCheck) {
            return false;
        }
        // 2. 文章如果传值了更新内容的大小不超过数据库的长度
        if (Objects.nonNull(articleContext.getArticleContent())
                && StringUtils.length(articleContext.getArticleContent()) > MAX_ARTICLE_CONTENT_LENGTH) {
            // 先打error日志
            log.error("Over MAX_ARTICLE_CONTENT_LENGTH");
            return false;
        }
        // 3. 必须包含一个标签
        List<Long> tagIds = articleContext.getTagIds();
        if (Objects.nonNull(tagIds) && CollectionUtils.isEmpty(tagIds)) {
            log.error("tag ids is empty");
            return false;
        }
        // 4. TODO 分类id合法
        // 5. 必须传文章id
        if (Objects.isNull(articleContext.getArticleId())) {
            log.error("article id is null");
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        BlogArticle blogArticle = Context2EntityConvert.articleContext2ArticleForUpdate(articleContext);
        blogArticleService.updateById(blogArticle);
    }
}
