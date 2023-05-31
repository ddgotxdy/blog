package top.ddgotxdy.api.service.impl;

import org.springframework.stereotype.Service;
import top.ddgotxdy.api.convert.ArticleApiParam2ClientParamConvert;
import top.ddgotxdy.api.convert.ArticleDTO2ViewConvert;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.queryparam.*;
import top.ddgotxdy.api.model.updateparam.ArticleBodyUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.CategoryUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.TagUpdateApiParam;
import top.ddgotxdy.api.model.view.*;
import top.ddgotxdy.api.service.BlogArticleBizService;
import top.ddgotxdy.common.client.BlogArticleClient;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.deleteparam.ArticleBodyDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.CategoryDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.TagDeleteParam;
import top.ddgotxdy.common.model.article.dto.ArticleBodyPageListDTO;
import top.ddgotxdy.common.model.article.dto.CategoryPageListDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.model.article.dto.TotalCountDTO;
import top.ddgotxdy.common.model.article.queryparam.ArticleBodyQueryParam;
import top.ddgotxdy.common.model.article.queryparam.CategoryQueryParam;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.common.model.article.recoverparam.ArticleBodyRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.CategoryRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.TagRecoverParam;
import top.ddgotxdy.common.model.article.updateparam.ArticleBodyUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ddgo
 * @description: 文章封装服务实现类
 */
@Service
public class BlogArticleBizServiceImpl implements BlogArticleBizService {
    @Resource
    private BlogArticleClient articleClient;

    @Override
    public IdView addArticleBody(ArticleBodyAddApiParam articleBodyAddApiParam) {
        ArticleBodyAddParam articleBodyAddParam
                = ArticleApiParam2ClientParamConvert.addApiParam2AddParam(articleBodyAddApiParam);
        ResultView<IdDTO> response = articleClient.addArticleBody(articleBodyAddParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView updateArticleBody(ArticleBodyUpdateApiParam articleBodyUpdateApiParam) {
        ArticleBodyUpdateParam articleBodyUpdateParam
                = ArticleApiParam2ClientParamConvert.updateApiParam2UpdateParam(articleBodyUpdateApiParam);
        ResultView<IdDTO> response = articleClient.updateArticleBody(articleBodyUpdateParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdsView deleteArticleBody(List<Long> articleIdList) {
        ArticleBodyDeleteParam articleBodyDeleteParam
                = ArticleApiParam2ClientParamConvert.articleBodyDeleteApiParam2deleteParam(articleIdList);
        ResultView<IdsDTO> response = articleClient.deleteArticleBody(articleBodyDeleteParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverArticleBody(List<Long> articleIdList) {
        ArticleBodyRecoverParam articleBodyRecoverParam
                = ArticleApiParam2ClientParamConvert.articleBodyRecoverApiParam2recoverParam(articleIdList);
        ResultView<IdsDTO> response = articleClient.recoverArticleBody(articleBodyRecoverParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public PageResult<ArticleBodyPageListView> queryArticleBodyByPage(PageQry<ArticleBodyQueryApiParam> articleBodyQueryApiParamPageQry) {
        PageQry<ArticleBodyQueryParam> articleBodyQueryParamPageQry
                = ArticleApiParam2ClientParamConvert.articleBodyQueryApiParam2QueryParam(articleBodyQueryApiParamPageQry);
        ResultView<PageResult<ArticleBodyPageListDTO>> response = articleClient.queryArticleBodyByPage(articleBodyQueryParamPageQry);
        PageResult<ArticleBodyPageListView> articleBodyPageListViewPageResult
                = ArticleDTO2ViewConvert.articleBodyPageListDTO2View(response.checkAndGetData());
        return articleBodyPageListViewPageResult;
    }

    @Override
    public IdView addTag(TagAddApiParam tagAddApiParam) {
        TagAddParam tagAddParam
                = ArticleApiParam2ClientParamConvert.addApiParam2AddParam(tagAddApiParam);
        ResultView<IdDTO> response = articleClient.addTag(tagAddParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView updateTag(TagUpdateApiParam tagUpdateApiParam) {
        TagUpdateParam tagUpdateParam
                = ArticleApiParam2ClientParamConvert.updateApiParam2UpdateParam(tagUpdateApiParam);
        ResultView<IdDTO> response = articleClient.updateTag(tagUpdateParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdsView deleteTag(List<Long> tagList) {
        TagDeleteParam tagDeleteParam
                = ArticleApiParam2ClientParamConvert.tagDeleteApiParam2deleteParam(tagList);
        ResultView<IdsDTO> response = articleClient.deleteTag(tagDeleteParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverTag(List<Long> tagList) {
        TagRecoverParam tagRecoverParam
                = ArticleApiParam2ClientParamConvert.tagRecoverApiParam2recoverParam(tagList);
        ResultView<IdsDTO> response = articleClient.recoverTag(tagRecoverParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public PageResult<TagPageListView> queryTagByPage(PageQry<TagQueryApiParam> tagQueryApiParamPageQry) {
        PageQry<TagQueryParam> tagQueryParam
                = ArticleApiParam2ClientParamConvert.tagQueryApiParam2QueryParam(tagQueryApiParamPageQry);
        ResultView<PageResult<TagPageListDTO>> response = articleClient.queryTagByPage(tagQueryParam);
        PageResult<TagPageListView> tagPageListViewPageResult = ArticleDTO2ViewConvert.tagPageListDTO2View(response.checkAndGetData());
        return tagPageListViewPageResult;
    }

    @Override
    public IdView addCategory(CategoryAddApiParam categoryAddApiParam) {
        CategoryAddParam categoryAddParam
                = ArticleApiParam2ClientParamConvert.addApiParam2AddParam(categoryAddApiParam);
        ResultView<IdDTO> response = articleClient.addCategory(categoryAddParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView updateCategory(CategoryUpdateApiParam categoryUpdateApiParam) {
        CategoryUpdateParam categoryUpdateParam
                = ArticleApiParam2ClientParamConvert.updateApiParam2UpdateParam(categoryUpdateApiParam);
        ResultView<IdDTO> response = articleClient.updateCategory(categoryUpdateParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdsView deleteCategory(List<Long> categoryList) {
        CategoryDeleteParam categoryDeleteParam
                = ArticleApiParam2ClientParamConvert.categoryDeleteApiParam2deleteParam(categoryList);
        ResultView<IdsDTO> response = articleClient.deleteCategory(categoryDeleteParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverCategory(List<Long> categoryList) {
        CategoryRecoverParam categoryRecoverParam
                = ArticleApiParam2ClientParamConvert.categoryRecoverApiParam2recoverParam(categoryList);
        ResultView<IdsDTO> response = articleClient.recoverCategory(categoryRecoverParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public PageResult<CategoryPageListView> queryCategoryByPage(
            PageQry<CategoryQueryApiParam> categoryQueryApiParamPageQry
    ) {
        PageQry<CategoryQueryParam> categoryQueryParam
                = ArticleApiParam2ClientParamConvert.categoryQueryApiParam2QueryParam(categoryQueryApiParamPageQry);
        ResultView<PageResult<CategoryPageListDTO>> response
                = articleClient.queryCategoryByPage(categoryQueryParam);
        PageResult<CategoryPageListView> categoryPageListViewPageResult
                = ArticleDTO2ViewConvert.categoryPageListDTO2View(response.checkAndGetData());
        return categoryPageListViewPageResult;
    }

    @Override
    public PageResult<ArticleBodyPageListUserView> queryArticleBodyByPageUser(
            PageQry<ArticleBodyQueryApiUserParam> articleBodyQueryApiUserParamPageQry
    ) {
        PageQry<ArticleBodyQueryParam> articleBodyQueryParamPageQry
                = ArticleApiParam2ClientParamConvert.articleBodyQueryApiUserParam2QueryParam(articleBodyQueryApiUserParamPageQry);
        ResultView<PageResult<ArticleBodyPageListDTO>> response
                = articleClient.queryArticleBodyByPage(articleBodyQueryParamPageQry);
        PageResult<ArticleBodyPageListUserView> articleBodyPageListUserViewPageResult
                = ArticleDTO2ViewConvert.articleBodyPageListDTO2UserView(response.checkAndGetData());
        return articleBodyPageListUserViewPageResult;
    }

    @Override
    public PageResult<TagPageListView> queryTagByPageUser(PageQry<TagQueryApiUserParam> tagQueryApiUserParamPageQry) {
        PageQry<TagQueryParam> tagQueryParam
                = ArticleApiParam2ClientParamConvert.tagQueryApiUserParam2QueryParam(tagQueryApiUserParamPageQry);
        ResultView<PageResult<TagPageListDTO>> response = articleClient.queryTagByPage(tagQueryParam);
        PageResult<TagPageListView> tagPageListViewPageResult
                = ArticleDTO2ViewConvert.tagPageListDTO2View(response.checkAndGetData());
        return tagPageListViewPageResult;
    }

    @Override
    public PageResult<CategoryPageListView> queryCategoryByPageUser(
            PageQry<CategoryQueryApiUserParam> categoryQueryApiUserParamPageQry
    ) {
        PageQry<CategoryQueryParam> categoryQueryParam
                = ArticleApiParam2ClientParamConvert.categoryQueryApiUserParam2QueryParam(categoryQueryApiUserParamPageQry);
        ResultView<PageResult<CategoryPageListDTO>> response
                = articleClient.queryCategoryByPage(categoryQueryParam);
        PageResult<CategoryPageListView> categoryPageListViewPageResult
                = ArticleDTO2ViewConvert.categoryPageListDTO2View(response.checkAndGetData());
        return categoryPageListViewPageResult;
    }

    @Override
    public TotalCountView getTotalCount() {
        ResultView<TotalCountDTO> response = articleClient.getTotalCount();
        TotalCountView totalCountView
                = ArticleDTO2ViewConvert.totalDTO2View(response.checkAndGetData());
        return totalCountView;
    }
}
