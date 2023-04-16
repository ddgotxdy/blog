package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.queryparam.TagQueryApiParam;
import top.ddgotxdy.api.model.updateparam.TagUpdateApiParam;
import top.ddgotxdy.api.model.view.ArticleListView;
import top.ddgotxdy.api.model.view.TagPageListView;
import top.ddgotxdy.api.service.BlogArticleBizService;
import top.ddgotxdy.common.model.*;

import javax.annotation.Resource;
import java.util.List;

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

    //--------------------文章相关接口---------------------------------------------

    @ApiOperation("首页获取文章列表，分页获取")
    @PostMapping("/blog/list")
    public ResultView<PageResult<ArticleListView>> getArticleList() {
        PageResult<ArticleListView> articleList = articleBizService.getArticleList();
        return ResultView.success(articleList);
    }

    @ApiOperation("添加文章")
    @PostMapping("/admin/articleBody/add")
    public ResultView<IdView> addArticleBody(
            @Validated @RequestBody ArticleBodyAddApiParam articleBodyAddApiParam
    ) {
        IdView idView = articleBizService.addArticleBody(articleBodyAddApiParam);
        return ResultView.success(idView);
    }
    //--------------------标签相关接口---------------------------------------------

    @ApiOperation("添加标签")
    @PostMapping("/admin/tag/add")
    public ResultView<IdView> addTag(
            @Validated @RequestBody TagAddApiParam tagAddApiParam
    ) {
        IdView idView = articleBizService.addTag(tagAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("更新标签标签")
    @PostMapping("/admin/tag/update")
    public ResultView<IdView> updateTag(
            @Validated @RequestBody TagUpdateApiParam tagUpdateApiParam
    ) {
        IdView idView = articleBizService.updateTag(tagUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("标签分页查询")
    @PostMapping("admin/tag/queryByPage")
    public ResultView<PageResult<TagPageListView>> queryTagByPage(
            @Validated @RequestBody PageQry<TagQueryApiParam> tagQueryParamPageQry
    ) {
        PageResult<TagPageListView> result = articleBizService.queryTagByPage(tagQueryParamPageQry);
        return ResultView.success(result);
    }

    @ApiOperation("标签删除接口")
    @DeleteMapping("admin/tag/delete")
    public ResultView<IdsView> deleteTag(
            @RequestBody List<Long> tagList
    ) {
        IdsView idView = articleBizService.deleteTag(tagList);
        return ResultView.success(idView);
    }

    @ApiOperation("标签恢复接口")
    @PostMapping("admin/tag/recover")
    public ResultView<IdsView> recoverTag(
            @RequestBody List<Long> tagList
    ) {
        IdsView idView = articleBizService.recoverTag(tagList);
        return ResultView.success(idView);
    }

    //--------------------分类相关接口---------------------------------------------

    @ApiOperation("添加分类")
    @PostMapping("/admin/category/add")
    public ResultView<IdView> addCategory(
            @Validated @RequestBody CategoryAddApiParam categoryAddApiParam
    ) {
        IdView idView = articleBizService.addCategory(categoryAddApiParam);
        return ResultView.success(idView);
    }
}
