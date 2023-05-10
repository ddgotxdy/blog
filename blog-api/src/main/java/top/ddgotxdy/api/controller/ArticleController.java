package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.api.model.addparam.ArticleBodyAddApiParam;
import top.ddgotxdy.api.model.addparam.CategoryAddApiParam;
import top.ddgotxdy.api.model.addparam.TagAddApiParam;
import top.ddgotxdy.api.model.queryparam.ArticleBodyQueryApiParam;
import top.ddgotxdy.api.model.queryparam.CategoryQueryApiParam;
import top.ddgotxdy.api.model.queryparam.TagQueryApiParam;
import top.ddgotxdy.api.model.updateparam.ArticleBodyUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.CategoryUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.TagUpdateApiParam;
import top.ddgotxdy.api.model.view.ArticleBodyPageListView;
import top.ddgotxdy.api.model.view.CategoryPageListView;
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

    @ApiOperation("分页获取文章列表")
    @PostMapping("/articleBody/queryByPage")
    public ResultView<PageResult<ArticleBodyPageListView>> queryArticleBodyByPage(
            @Validated @RequestBody PageQry<ArticleBodyQueryApiParam> articleBodyQueryApiParamPageQry
    ) {
        PageResult<ArticleBodyPageListView> articleList = articleBizService.queryArticleBodyByPage(articleBodyQueryApiParamPageQry);
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

    @ApiOperation("更新文章")
    @PostMapping("/admin/articleBody/update")
    public ResultView<IdView> updateArticleBody(
            @Validated @RequestBody ArticleBodyUpdateApiParam articleBodyUpdateApiParam
    ) {
        IdView idView = articleBizService.updateArticleBody(articleBodyUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("文章删除接口")
    @DeleteMapping("/admin/articleBody/delete")
    public ResultView<IdsView> deleteArticleBody(
            @RequestBody List<Long> articleIdList
    ) {
        IdsView idView = articleBizService.deleteArticleBody(articleIdList);
        return ResultView.success(idView);
    }

    @ApiOperation("文章恢复接口")
    @PostMapping("admin/articleBody/recover")
    public ResultView<IdsView> recoverArticleBody(
            @RequestBody List<Long> articleIdList
    ) {
        IdsView idView = articleBizService.recoverArticleBody(articleIdList);
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

    @ApiOperation("更新标签")
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

    @ApiOperation("更新分类")
    @PostMapping("/admin/category/update")
    public ResultView<IdView> updateCategory(
            @Validated @RequestBody CategoryUpdateApiParam categoryUpdateApiParam
    ) {
        IdView idView = articleBizService.updateCategory(categoryUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("分类删除接口")
    @DeleteMapping("admin/category/delete")
    public ResultView<IdsView> deleteCategory(
            @RequestBody List<Long> categoryList
    ) {
        IdsView idView = articleBizService.deleteCategory(categoryList);
        return ResultView.success(idView);
    }

    @ApiOperation("分类恢复接口")
    @PostMapping("admin/category/recover")
    public ResultView<IdsView> recoverCategory(
            @RequestBody List<Long> categoryList
    ) {
        IdsView idView = articleBizService.recoverCategory(categoryList);
        return ResultView.success(idView);
    }

    @ApiOperation("分类分页查询")
    @PostMapping("admin/category/queryByPage")
    public ResultView<PageResult<CategoryPageListView>> queryCategoryByPage(
            @Validated @RequestBody PageQry<CategoryQueryApiParam> categoryQueryApiParamPageQry
    ) {
        PageResult<CategoryPageListView> result = articleBizService.queryCategoryByPage(categoryQueryApiParamPageQry);
        return ResultView.success(result);
    }

}
