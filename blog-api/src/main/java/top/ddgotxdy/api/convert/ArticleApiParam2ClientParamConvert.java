package top.ddgotxdy.api.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.queryparam.CategoryQueryApiParam;
import top.ddgotxdy.api.model.queryparam.TagQueryApiParam;
import top.ddgotxdy.api.model.updateparam.CategoryUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.TagUpdateApiParam;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.deleteparam.CategoryDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.TagDeleteParam;
import top.ddgotxdy.common.model.article.queryparam.CategoryQueryParam;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.common.model.article.recoverparam.CategoryRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.TagRecoverParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.List;

/**
 * @author: ddgo
 * @description: 请求参数远程服务的请求参数
 */
public class ArticleApiParam2ClientParamConvert {
    private ArticleApiParam2ClientParamConvert() { }
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

    public static TagDeleteParam tagDeleteApiParam2deleteParam(List<Long> tagList) {
        TagDeleteParam tagDeleteParam = new TagDeleteParam();
        tagDeleteParam.setTagIds(tagList);
        Long userId = ContextScope.getUserId();
        tagDeleteParam.setUserId(userId);
        return tagDeleteParam;
    }

    public static TagRecoverParam tagRecoverApiParam2recoverParam(List<Long> tagList) {
        TagRecoverParam tagRecoverParam = new TagRecoverParam();
        tagRecoverParam.setTagIds(tagList);
        Long userId = ContextScope.getUserId();
        tagRecoverParam.setUserId(userId);
        return tagRecoverParam;
    }

    public static CategoryUpdateParam updateApiParam2UpdateParam(CategoryUpdateApiParam categoryUpdateApiParam) {
        CategoryUpdateParam categoryUpdateParam = new CategoryUpdateParam();
        BeanCopyUtil.copyProperties(categoryUpdateApiParam, categoryUpdateParam);
        Long userId = ContextScope.getUserId();
        categoryUpdateParam.setUserId(userId);
        return categoryUpdateParam;
    }


    public static CategoryDeleteParam categoryDeleteApiParam2deleteParam(List<Long> categoryList) {
        CategoryDeleteParam categoryDeleteParam = new CategoryDeleteParam();
        categoryDeleteParam.setCategoryIds(categoryList);
        Long userId = ContextScope.getUserId();
        categoryDeleteParam.setUserId(userId);
        return categoryDeleteParam;
    }

    public static CategoryRecoverParam categoryRecoverApiParam2recoverParam(List<Long> categoryList) {
        CategoryRecoverParam categoryRecoverParam = new CategoryRecoverParam();
        categoryRecoverParam.setCategoryIds(categoryList);
        Long userId = ContextScope.getUserId();
        categoryRecoverParam.setUserId(userId);
        return categoryRecoverParam;
    }

    //--------------------------------------查询---------------------------------------

    public static PageQry<TagQueryParam> tagQueryApiParam2QueryParam(PageQry<TagQueryApiParam> tagQueryApiParamPageQry) {
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

    public static PageQry<CategoryQueryParam> categoryQueryApiParam2QueryParam(PageQry<CategoryQueryApiParam> categoryQueryApiParamPageQry) {
        CategoryQueryParam categoryQueryParam = new CategoryQueryParam();
        // 范型复制
        CategoryQueryApiParam categoryQueryApiParam = categoryQueryApiParamPageQry.getQueryParam();
        BeanCopyUtil.copyProperties(categoryQueryApiParam, categoryQueryParam);
        // 分页参数复制
        PageQry<CategoryQueryParam> categoryQueryParamPageQry = new PageQry<>();
        BeanCopyUtil.copyProperties(categoryQueryApiParamPageQry, categoryQueryParamPageQry);
        // 范型赋值进去
        categoryQueryParamPageQry.setQueryParam(categoryQueryParam);
        return categoryQueryParamPageQry;
    }
}
