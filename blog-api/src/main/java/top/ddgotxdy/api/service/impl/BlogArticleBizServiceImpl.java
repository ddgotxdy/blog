package top.ddgotxdy.api.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import top.ddgotxdy.api.convert.ArticleApiParam2ClientParamConvert;
import top.ddgotxdy.api.convert.DTO2ViewConvert;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.queryparam.CategoryQueryApiParam;
import top.ddgotxdy.api.model.queryparam.TagQueryApiParam;
import top.ddgotxdy.api.model.updateparam.CategoryUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.TagUpdateApiParam;
import top.ddgotxdy.api.model.view.ArticleListView;
import top.ddgotxdy.api.model.view.CategoryPageListView;
import top.ddgotxdy.api.model.view.TagPageListView;
import top.ddgotxdy.api.service.BlogArticleBizService;
import top.ddgotxdy.common.client.BlogArticleClient;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.article.ArticleListDTO;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.deleteparam.CategoryDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.TagDeleteParam;
import top.ddgotxdy.common.model.article.dto.CategoryPageListDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.model.article.queryparam.CategoryQueryParam;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.common.model.article.recoverparam.CategoryRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.TagRecoverParam;
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
    public PageResult<ArticleListView> getArticleList() {
        ResultView<PageResult<ArticleListDTO>> articleList = articleClient.getArticleList();
        // TODO 简单转换一下
        ArticleListView articleListView = new ArticleListView();
        articleListView.setArticleId(articleList.getData().getData().get(0).getArticleId());
        List<ArticleListView> articleListViewList = Lists.newArrayList(articleListView);
        PageResult<ArticleListView> pageResult = new PageResult<>();
        pageResult.setData(articleListViewList);
        pageResult.setTotalNumber(articleList.getData().getTotalNumber());
        return pageResult;
    }

    @Override
    public IdView addArticleBody(ArticleBodyAddApiParam articleBodyAddApiParam) {
        ArticleBodyAddParam articleBodyAddParam = ArticleApiParam2ClientParamConvert.addApiParam2AddParam(articleBodyAddApiParam);
        ResultView<IdDTO> response = articleClient.addArticle(articleBodyAddParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView addTag(TagAddApiParam tagAddApiParam) {
        TagAddParam tagAddParam = ArticleApiParam2ClientParamConvert.addApiParam2AddParam(tagAddApiParam);
        ResultView<IdDTO> response = articleClient.addTag(tagAddParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView updateTag(TagUpdateApiParam tagUpdateApiParam) {
        TagUpdateParam tagUpdateParam = ArticleApiParam2ClientParamConvert.updateApiParam2UpdateParam(tagUpdateApiParam);
        ResultView<IdDTO> response = articleClient.updateTag(tagUpdateParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdsView deleteTag(List<Long> tagList) {
        TagDeleteParam tagDeleteParam = ArticleApiParam2ClientParamConvert.tagDeleteApiParam2deleteParam(tagList);
        ResultView<IdsDTO> response = articleClient.deleteTag(tagDeleteParam);
        IdsDTO idsDTO = response.getData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverTag(List<Long> tagList) {
        TagRecoverParam tagRecoverParam = ArticleApiParam2ClientParamConvert.tagRecoverApiParam2recoverParam(tagList);
        ResultView<IdsDTO> response = articleClient.recoverTag(tagRecoverParam);
        IdsDTO idsDTO = response.getData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public PageResult<TagPageListView> queryTagByPage(PageQry<TagQueryApiParam> tagQueryParamPageQry) {
        PageQry<TagQueryParam> tagQueryParam = ArticleApiParam2ClientParamConvert.tagQueryApiParam2QueryParam(tagQueryParamPageQry);
        ResultView<PageResult<TagPageListDTO>> response = articleClient.queryTagByPage(tagQueryParam);
        PageResult<TagPageListView> tagPageListViewPageResult = DTO2ViewConvert.tagPageListDTO2View(response.getData());
        return tagPageListViewPageResult;
    }

    @Override
    public IdView addCategory(CategoryAddApiParam categoryAddApiParam) {
        CategoryAddParam categoryAddParam = ArticleApiParam2ClientParamConvert.addApiParam2AddParam(categoryAddApiParam);
        ResultView<IdDTO> response = articleClient.addCategory(categoryAddParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView updateCategory(CategoryUpdateApiParam categoryUpdateApiParam) {
        CategoryUpdateParam categoryUpdateParam = ArticleApiParam2ClientParamConvert.updateApiParam2UpdateParam(categoryUpdateApiParam);
        ResultView<IdDTO> response = articleClient.updateCategory(categoryUpdateParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdsView deleteCategory(List<Long> categoryList) {
        CategoryDeleteParam categoryDeleteParam = ArticleApiParam2ClientParamConvert.categoryDeleteApiParam2deleteParam(categoryList);
        ResultView<IdsDTO> response = articleClient.deleteCategory(categoryDeleteParam);
        IdsDTO idsDTO = response.getData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverCategory(List<Long> categoryList) {
        CategoryRecoverParam categoryRecoverParam = ArticleApiParam2ClientParamConvert.categoryRecoverApiParam2recoverParam(categoryList);
        ResultView<IdsDTO> response = articleClient.recoverCategory(categoryRecoverParam);
        IdsDTO idsDTO = response.getData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public PageResult<CategoryPageListView> queryCategoryByPage(PageQry<CategoryQueryApiParam> categoryQueryApiParamPageQry) {
        PageQry<CategoryQueryParam> categoryQueryParam = ArticleApiParam2ClientParamConvert.categoryQueryApiParam2QueryParam(categoryQueryApiParamPageQry);
        ResultView<PageResult<CategoryPageListDTO>> response = articleClient.queryCategoryByPage(categoryQueryParam);
        PageResult<CategoryPageListView> categoryPageListViewPageResult = DTO2ViewConvert.categoryPageListDTO2View(response.getData());
        return categoryPageListViewPageResult;
    }
}
