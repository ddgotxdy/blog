package top.ddgotxdy.api.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.scope.ContextScope;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;

/**
 * @author: ddgo
 * @description: 请求参数远程服务的请求参数
 */
public class ApiParam2ClientParamConvert {
    private ApiParam2ClientParamConvert() { }
    public static ArticleBodyAddParam AddApiParam2AddParam(ArticleBodyAddApiParam articleBodyAddApiParam) {
        ArticleBodyAddParam articleBodyAddParam = new ArticleBodyAddParam();
        BeanUtils.copyProperties(articleBodyAddApiParam, articleBodyAddParam);
        Long userId = ContextScope.getUserId();
        articleBodyAddParam.setUserId(userId);
        return articleBodyAddParam;
    }

    public static TagAddParam AddApiParam2AddParam(TagAddApiParam tagAddApiParam) {
        TagAddParam tagAddParam = new TagAddParam();
        BeanUtils.copyProperties(tagAddApiParam, tagAddParam);
        Long userId = ContextScope.getUserId();
        tagAddParam.setUserId(userId);
        return tagAddParam;
    }

    public static CategoryAddParam AddApiParam2AddParam(CategoryAddApiParam categoryAddApiParam) {
        CategoryAddParam categoryAddParam = new CategoryAddParam();
        BeanUtils.copyProperties(categoryAddApiParam, categoryAddParam);
        Long userId = ContextScope.getUserId();
        categoryAddParam.setUserId(userId);
        return categoryAddParam;
    }
}
