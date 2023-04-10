package top.ddgotxdy.api.service.impl;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import top.ddgotxdy.api.convert.ApiParam2ClientParamConvert;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.view.ArticleListView;
import top.ddgotxdy.api.service.BlogArticleBizService;
import top.ddgotxdy.common.client.BlogArticleClient;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.article.ArticleListDTO;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;

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
        ArticleBodyAddParam articleBodyAddParam = ApiParam2ClientParamConvert.AddApiParam2AddParam(articleBodyAddApiParam);
        ResultView<IdDTO> response = articleClient.addArticle(articleBodyAddParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView addTag(TagAddApiParam tagAddApiParam) {
        TagAddParam tagAddParam = ApiParam2ClientParamConvert.AddApiParam2AddParam(tagAddApiParam);
        ResultView<IdDTO> response = articleClient.addTag(tagAddParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView addCategory(CategoryAddApiParam categoryAddApiParam) {
        CategoryAddParam categoryAddParam = ApiParam2ClientParamConvert.AddApiParam2AddParam(categoryAddApiParam);
        ResultView<IdDTO> response = articleClient.addCategory(categoryAddParam);
        IdDTO idDTO = response.getData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }
}
