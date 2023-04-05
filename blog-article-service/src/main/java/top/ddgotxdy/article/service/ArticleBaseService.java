package top.ddgotxdy.article.service;

import top.ddgotxdy.article.model.ArticleContext;

/**
 * @author: ddgo
 * @description:
 */
public interface ArticleBaseService {
    /**
     * 传入事件，根据事件进行事件的调用
     * @param articleContext 文章上下文
     */
    void execute(ArticleContext articleContext);
}
