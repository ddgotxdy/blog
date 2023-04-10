package top.ddgotxdy.article.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;

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

}
