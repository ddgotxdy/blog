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
        // 通过校验，操作日志预落库
        preOplog(articleContext);
        // 开始执行文章操作
        doExecute(articleContext);
        // 已经落库，操作日志实际落库
        afterOplog(articleContext);
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

    /**
     * 操作日志预落库
     * @param articleContext 文章上下文
     */
    protected void preOplog(ArticleContext articleContext) {
        // TODO 操作日志记录
    }

    /**
     * 操作日志后落库
     * @param articleContext 文章上下文
     */
    protected void afterOplog(ArticleContext articleContext) {
        // TODO 操作日志记录
    }
}
