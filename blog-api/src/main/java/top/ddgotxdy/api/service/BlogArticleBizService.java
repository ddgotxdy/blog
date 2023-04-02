package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.view.ArticleListView;
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
}
