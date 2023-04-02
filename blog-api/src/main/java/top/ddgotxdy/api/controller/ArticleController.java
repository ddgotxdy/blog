package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ddgotxdy.api.model.view.ArticleListView;
import top.ddgotxdy.api.service.BlogArticleBizService;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.ResultView;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description: 文章接口
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章接口")
public class ArticleController {
    @Resource
    private BlogArticleBizService articleBizService;

    @ApiOperation("首页获取文章列表，分页获取")
    @PostMapping("/list")
    public ResultView<PageResult<ArticleListView>> getArticleList() {
        PageResult<ArticleListView> articleList = articleBizService.getArticleList();
        return ResultView.success(articleList);
    }
}
