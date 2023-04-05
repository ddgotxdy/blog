package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogArticleService;

import javax.annotation.Resource;

import static top.ddgotxdy.article.constant.ValidateConstant.MAX_ARTICLE_CONTENT_LENGTH;

/**
 * @author: ddgo
 * @description: 文章添加服务
 */
@ArticleEventSelector(ArticleEvent.ARTICLE_ADD)
@Service
@Slf4j
public class ArticleAddServiceImpl extends AbstractArticleService {
    @Resource
    private BlogArticleService blogArticleService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 1. TODO 用户的权限必须是管理员，否者不允许创建文章
        // 2. 文章内容的大小不超过数据库的长度
        if (StringUtils.length(articleContext.getArticleContent()) > MAX_ARTICLE_CONTENT_LENGTH) {
            // 先打error日志
            log.error(" Over MAX_ARTICLE_CONTENT_LENGTH");
            return false;
        }
        return true;
    }

    @Override
    protected void doExecute(ArticleContext articleContext) {
        // TODO 先直接Mock
        articleContext.setArticleId(520L);
    }
}
