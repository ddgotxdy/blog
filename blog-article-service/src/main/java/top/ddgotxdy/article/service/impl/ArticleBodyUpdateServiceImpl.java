package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;

/**
 * @author: ddgo
 * @description: 文章更新服务
 */
@ArticleEventSelector(ArticleEvent.ARTICLE_BODY_UPDATE)
@Service
@Slf4j
public class ArticleBodyUpdateServiceImpl extends AbstractArticleService {
    @Override
    protected boolean filter(ArticleContext articleContext) {
        return false;
    }

    @Override
    protected void doExecute(ArticleContext articleContext) {

    }
}
