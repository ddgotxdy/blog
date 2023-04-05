package top.ddgotxdy.article.service;

import top.ddgotxdy.article.model.ArticleContext;

/**
 * @author: ddgo
 * @description: 文章服务抽象类
 */
public abstract class AbstractArticleService implements ArticleBaseService {
    @Override
    public void execute(ArticleContext articleContext) {
        // 过滤条件不满足
        if (!filter(articleContext)) {
            return;
        }
        // 开始执行文章操作
        doExecute(articleContext);
    }

    /**
     * 合法性校验
     * @param articleContext 文章上下文
     * @return 是否通过校验
     */
    protected abstract boolean filter(ArticleContext articleContext);

    /**
     * 开始执行操作
     * @param articleContext 文章上下文
     */
    protected abstract void doExecute(ArticleContext articleContext);
}
