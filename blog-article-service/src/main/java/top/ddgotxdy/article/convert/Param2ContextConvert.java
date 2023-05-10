package top.ddgotxdy.article.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.deleteparam.ArticleBodyDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.CategoryDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.TagDeleteParam;
import top.ddgotxdy.common.model.article.recoverparam.ArticleBodyRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.CategoryRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.TagRecoverParam;
import top.ddgotxdy.common.model.article.updateparam.ArticleBodyUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;

/**
 * @author: ddgo
 * @description: 文章请求参数转换为ArticleContext
 */
public class Param2ContextConvert {
    private Param2ContextConvert() { }

    /**
     * 将文章添加参数转换为文章上下文
     * @param articleBodyAddParam 文章添加参数
     * @return 文章上下文
     */
    public static ArticleContext addParamConvert(ArticleBodyAddParam articleBodyAddParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(articleBodyAddParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.ARTICLE_BODY_ADD);
        return articleContext;
    }

    /**
     * 将标签添加参数转换为文章上下文
     * @param tagAddParam 标签添加参数
     * @return 文章上下文
     */
    public static ArticleContext addParamConvert(TagAddParam tagAddParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(tagAddParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.TAG_ADD);
        return articleContext;
    }

    /**
     * 将分类添加参数转换为文章上下文
     * @param categoryAddParam 分类添加参数
     * @return 文章上下文
     */
    public static ArticleContext addParamConvert(CategoryAddParam categoryAddParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(categoryAddParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.CATEGORY_ADD);
        return articleContext;
    }

    /**
     * 将文章修改参数转换为文章上下文
     * @param articleBodyUpdateParam 文章修改参数
     * @return 文章上下文
     */
    public static ArticleContext updateParamContext(ArticleBodyUpdateParam articleBodyUpdateParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(articleBodyUpdateParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.ARTICLE_BODY_UPDATE);
        return articleContext;
    }

    /**
     * 将标签修改参数转换为文章上下文
     * @param tagUpdateParam 标签修改参数
     * @return 文章上下文
     */
    public static ArticleContext updateParamContext(TagUpdateParam tagUpdateParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(tagUpdateParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.TAG_UPDATE);
        return articleContext;
    }

    /**
     * 将分类修改参数转换为文章上下文
     * @param categoryUpdateParam 分类修改参数
     * @return 文章上下文
     */
    public static ArticleContext updateParamContext(CategoryUpdateParam categoryUpdateParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(categoryUpdateParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.CATEGORY_UPDATE);
        return articleContext;
    }

    public static ArticleContext deleteParamContext(TagDeleteParam tagDeleteParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(tagDeleteParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.TAG_DELETE);
        return articleContext;
    }

    public static ArticleContext recoverParamContext(TagRecoverParam tagRecoverParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(tagRecoverParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.TAG_RECOVER);
        return articleContext;
    }

    public static ArticleContext deleteParamContext(CategoryDeleteParam categoryDeleteParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(categoryDeleteParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.CATEGORY_DELETE);
        return articleContext;
    }

    public static ArticleContext recoverParamContext(CategoryRecoverParam categoryRecoverParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(categoryRecoverParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.CATEGORY_RECOVER);
        return articleContext;
    }

    public static ArticleContext deleteParamContext(ArticleBodyDeleteParam articleBodyDeleteParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(articleBodyDeleteParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.ARTICLE_BODY_DELETE);
        return articleContext;
    }

    public static ArticleContext recoverParamContext(ArticleBodyRecoverParam articleBodyRecoverParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(articleBodyRecoverParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.ARTICLE_BODY_RECOVER);
        return articleContext;
    }
}
