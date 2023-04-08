package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogTagService;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description: 标签更新服务类
 */
@ArticleEventSelector(ArticleEvent.TAG_UPDATE)
@Component
@Slf4j
public class TagUpdateServiceImpl extends AbstractArticleService {
    @Resource
    private BlogTagService blogTagService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        return false;
    }

    @Override
    protected void doExecute(ArticleContext articleContext) {

    }
}
