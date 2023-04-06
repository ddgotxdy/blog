package top.ddgotxdy.api.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.api.model.addparam.ArticleAddApiParam;
import top.ddgotxdy.common.model.article.addparam.ArticleAddParam;

/**
 * @author: ddgo
 * @description: 请求参数远程服务的请求参数
 */
public class ApiParam2ClientParamConvert {
    private ApiParam2ClientParamConvert() { }
    public static ArticleAddParam ArticleAddApiParam2ArticleAddParam(ArticleAddApiParam articleAddApiParam) {
        ArticleAddParam articleAddParam = new ArticleAddParam();
        BeanUtils.copyProperties(articleAddApiParam, articleAddParam);
        return articleAddParam;
    }
}
