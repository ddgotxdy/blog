package top.ddgotxdy.article.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.common.model.article.addparam.ArticleAddParam;

/**
 * @author: ddgo
 * @description: 文章请求参数转换为ArticleContext
 */
public class Param2ContextConvert {
    private Param2ContextConvert() { }
    public static ArticleContext addParamConvert(ArticleAddParam articleAddParam) {
        ArticleContext articleContext = new ArticleContext();
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.ARTICLE_ADD);
        // 其它值的复制，目前没有特殊值
        BeanUtils.copyProperties(articleAddParam, articleContext);
        return articleContext;
    }
}
