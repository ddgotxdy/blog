package top.ddgotxdy.api.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ddgotxdy.api.model.view.ArticleListView;
import top.ddgotxdy.api.service.BlogArticleBizService;
import top.ddgotxdy.common.client.BlogArticleClient;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.article.ArticleListDTO;

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
}
