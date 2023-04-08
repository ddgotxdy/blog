package top.ddgotxdy.article.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.dal.entity.BlogArticle;

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
}
