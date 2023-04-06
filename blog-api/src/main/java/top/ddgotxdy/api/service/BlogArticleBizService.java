package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.addparam.ArticleAddApiParam;
import top.ddgotxdy.api.model.view.ArticleListView;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.PageResult;

/**
 * @author: ddgo
 * @description: 文章封装类
 */
public interface BlogArticleBizService {
    /**
     * 获取首页的文章列表
     * @return PageResult<ArticleListView>
     */
    PageResult<ArticleListView> getArticleList();

    /**
     * 添加文章
     * @param articleAddApiParam 添加文章请求参数
     * @return 文章的id
     */
    IdView addArticle(ArticleAddApiParam articleAddApiParam);
}
