package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.view.ArticleListView;
import top.ddgotxdy.api.service.BlogArticleBizService;
import top.ddgotxdy.common.model.IdView;
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
    @PostMapping("/blog/list")
    public ResultView<PageResult<ArticleListView>> getArticleList() {
        PageResult<ArticleListView> articleList = articleBizService.getArticleList();
        return ResultView.success(articleList);
    }

    @ApiOperation("添加文章")
    @PostMapping("/admin/add/articleBody")
    public ResultView<IdView> addArticleBody(
            @Validated @RequestBody ArticleBodyAddApiParam articleBodyAddApiParam
    ) {
        IdView idView = articleBizService.addArticleBody(articleBodyAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("添加标签")
    @PostMapping("/admin/add/tag")
    public ResultView<IdView> addTag(
            @Validated @RequestBody TagAddApiParam tagAddApiParam
    ) {
        IdView idView = articleBizService.addTag(tagAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("添加分类")
    @PostMapping("/admin/add/category")
    public ResultView<IdView> addCategory(
            @Validated @RequestBody CategoryAddApiParam categoryAddApiParam
    ) {
        IdView idView = articleBizService.addCategory(categoryAddApiParam);
        return ResultView.success(idView);
    }

}
