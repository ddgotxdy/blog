package top.ddgotxdy.article.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.dal.entity.BlogArticle;
import top.ddgotxdy.dal.entity.BlogCategory;
import top.ddgotxdy.dal.entity.BlogTag;

/**
 * @author: ddgo
 * @description: ArticleContext转实体对象
 */
public class Context2EntityConvert {
    private Context2EntityConvert() { }

    /**
     * ArticleContext  <===> Article
     * @param articleContext 文章上下文
     * @return Article
     */
    public static BlogArticle articleContext2Article(ArticleContext articleContext) {
        // 目前直接beanUtils就行
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(articleContext, blogArticle);
        return blogArticle;
    }

    /**
     * ArticleContext  <===> Tag
     * @param articleContext 文章上下文
     * @return BlogTag
     */
    public static BlogTag articleContext2Tag(ArticleContext articleContext) {
        // 目前直接beanUtils就行
        BlogTag blogTag = new BlogTag();
        BeanUtils.copyProperties(articleContext, blogTag);
        return blogTag;
    }

    /**
     * ArticleContext  <===> Category
     * @param articleContext 文章上下文
     * @return Article
     */
    public static BlogCategory articleContext2Category(ArticleContext articleContext) {
        // 目前直接beanUtils就行
        BlogCategory blogCategory = new BlogCategory();
        BeanUtils.copyProperties(articleContext, blogCategory);
        return blogCategory;
    }
}
