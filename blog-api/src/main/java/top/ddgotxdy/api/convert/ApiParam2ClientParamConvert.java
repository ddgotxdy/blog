package top.ddgotxdy.api.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.queryparam.TagQueryApiParam;
import top.ddgotxdy.api.model.updateparam.TagUpdateApiParam;
import top.ddgotxdy.api.scope.ContextScope;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;
import top.ddgotxdy.common.util.BeanCopyUtil;

/**
 * @author: ddgo
 * @description: 请求参数远程服务的请求参数
 */
public class ApiParam2ClientParamConvert {
    private ApiParam2ClientParamConvert() { }
    public static ArticleBodyAddParam addApiParam2AddParam(ArticleBodyAddApiParam articleBodyAddApiParam) {
        ArticleBodyAddParam articleBodyAddParam = new ArticleBodyAddParam();
        BeanUtils.copyProperties(articleBodyAddApiParam, articleBodyAddParam);
        Long userId = ContextScope.getUserId();
        articleBodyAddParam.setUserId(userId);
        return articleBodyAddParam;
    }

    public static TagAddParam addApiParam2AddParam(TagAddApiParam tagAddApiParam) {
        TagAddParam tagAddParam = new TagAddParam();
        BeanCopyUtil.copyProperties(tagAddApiParam, tagAddParam);
        Long userId = ContextScope.getUserId();
        tagAddParam.setUserId(userId);
        return tagAddParam;
    }

    public static CategoryAddParam addApiParam2AddParam(CategoryAddApiParam categoryAddApiParam) {
        CategoryAddParam categoryAddParam = new CategoryAddParam();
        BeanCopyUtil.copyProperties(categoryAddApiParam, categoryAddParam);
        Long userId = ContextScope.getUserId();
        categoryAddParam.setUserId(userId);
        return categoryAddParam;
    }

    public static TagUpdateParam updateApiParam2UpdateParam(TagUpdateApiParam tagUpdateApiParam) {
        TagUpdateParam tagUpdateParam = new TagUpdateParam();
        BeanCopyUtil.copyProperties(tagUpdateApiParam, tagUpdateParam);
        Long userId = ContextScope.getUserId();
        tagUpdateParam.setUserId(userId);
        return tagUpdateParam;
    }

    public static PageQry<TagQueryParam> queryApiParam2QueryParam(PageQry<TagQueryApiParam> tagQueryApiParamPageQry) {
        TagQueryParam tagQueryParam = new TagQueryParam();
        // 范型复制
        TagQueryApiParam tagQueryApiParam = tagQueryApiParamPageQry.getQueryParam();
        BeanCopyUtil.copyProperties(tagQueryApiParam, tagQueryParam);
        // 分页参数复制
        PageQry<TagQueryParam> tagQueryParamPageQry = new PageQry<>();
        BeanCopyUtil.copyProperties(tagQueryApiParamPageQry, tagQueryParamPageQry);
        // 范型赋值进去
        tagQueryParamPageQry.setQueryParam(tagQueryParam);
        return tagQueryParamPageQry;
    }

}
