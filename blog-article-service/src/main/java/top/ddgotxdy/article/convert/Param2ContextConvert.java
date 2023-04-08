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

    /**
     * 将文章添加参数转换为文章上下文
     * @param articleAddParam 文章添加参数
     * @return 文章上下文
     */
    public static ArticleContext addParamConvert(ArticleAddParam articleAddParam) {
        ArticleContext articleContext = new ArticleContext();
        // 先对无需处理的值复制一份
        BeanUtils.copyProperties(articleAddParam, articleContext);
        // 设置为添加事件
        articleContext.setArticleEvent(ArticleEvent.ARTICLE_BODY_ADD);
        return articleContext;
    }
}
