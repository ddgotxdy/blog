package top.ddgotxdy.article.controller;

import com.google.common.collect.Lists;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.article.service.ArticleCmdBizService;
import top.ddgotxdy.article.service.ArticleQueryBizService;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.article.ArticleListDTO;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.deleteparam.CategoryDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.TagDeleteParam;
import top.ddgotxdy.common.model.article.dto.CategoryDTO;
import top.ddgotxdy.common.model.article.dto.CategoryPageListDTO;
import top.ddgotxdy.common.model.article.dto.TagDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.model.article.queryparam.CategoryQueryParam;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.common.model.article.recoverparam.CategoryRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.TagRecoverParam;
import top.ddgotxdy.common.model.article.updateparam.ArticleBodyUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ddgo
 * @description: 文章控制器
 */
@RestController
@RequestMapping("openfeign/article")
public class ArticleController {
    @Resource
    private ArticleCmdBizService articleCmdBizService;
    @Resource
    private ArticleQueryBizService articleQueryBizService;

    @PostMapping("/list")
    public ResultView<PageResult<ArticleListDTO>> getArticleList() {
        // TODO 这里先Mock数据回去
        ArticleListDTO articleListDTO = new ArticleListDTO();
        articleListDTO.setArticleId(12345678900L);
        List<ArticleListDTO> articleListDTOList = Lists.newArrayList(articleListDTO);
        PageResult<ArticleListDTO> pageResult = new PageResult<>();
        pageResult.setData(articleListDTOList);
        pageResult.setTotalNumber(10L);
        return ResultView.success(pageResult);
    }

    @PostMapping("/body/add")
    public ResultView<IdDTO> addArticle(
            @RequestBody ArticleBodyAddParam articleBodyAddParam
    ) {
        IdDTO idDTO = articleCmdBizService.addArticleBody(articleBodyAddParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/body/update")
    public ResultView<IdDTO> updateArticle(
            @RequestBody ArticleBodyUpdateParam articleBodyUpdateParam
    ) {
        IdDTO idDTO = articleCmdBizService.updateArticleBody(articleBodyUpdateParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/tag/add")
    public ResultView<IdDTO> addTag(
            @Validated @RequestBody TagAddParam tagAddParam
    ) {
        IdDTO idDTO = articleCmdBizService.addTag(tagAddParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/tag/queryByPage")
    public ResultView<PageResult<TagPageListDTO>> queryTagByPage(
            @Validated @RequestBody PageQry<TagQueryParam> tagQueryParamPageQry
    ) {
        PageResult<TagPageListDTO> result = articleQueryBizService.queryTagByPage(tagQueryParamPageQry);
        return ResultView.success(result);
    }

    @GetMapping("/tag/queryById/{tagId}")
    public ResultView<TagDTO> queryTagById(
            @PathVariable("tagId") Long tagId
    ) {
        TagDTO result = articleQueryBizService.queryTagById(tagId);
        return ResultView.success(result);
    }

    @PostMapping("/tag/update")
    public ResultView<IdDTO> updateTag(
            @Validated @RequestBody TagUpdateParam tagUpdateParam
    ) {
        IdDTO idDTO = articleCmdBizService.updateTag(tagUpdateParam);
        return ResultView.success(idDTO);
    }

    @DeleteMapping("/tag/delete")
    public ResultView<IdsDTO> deleteTag(
            @RequestBody TagDeleteParam tagDeleteParam
    ) {
        IdsDTO idsDTO = articleCmdBizService.deleteTag(tagDeleteParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("/tag/recover")
    public ResultView<IdsDTO> recoverTag(
            @RequestBody TagRecoverParam tagRecoverParam
    ) {
        IdsDTO idsDTO = articleCmdBizService.recoverTag(tagRecoverParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("/category/add")
    public ResultView<IdDTO> addCategory(
            @Validated @RequestBody CategoryAddParam categoryAddParam
    ) {
        IdDTO idDTO = articleCmdBizService.addCategory(categoryAddParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/category/update")
    public ResultView<IdDTO> updateCategory(
            @Validated @RequestBody CategoryUpdateParam categoryUpdateParam
    ) {
        IdDTO idDTO = articleCmdBizService.updateCategory(categoryUpdateParam);
        return ResultView.success(idDTO);
    }

    @DeleteMapping("/category/delete")
    public ResultView<IdsDTO> deleteCategory(
            @RequestBody CategoryDeleteParam categoryDeleteParam
    ) {
        IdsDTO idsDTO = articleCmdBizService.deleteCategory(categoryDeleteParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("/category/recover")
    public ResultView<IdsDTO> recoverCategory(
            @RequestBody CategoryRecoverParam categoryRecoverParam
    ) {
        IdsDTO idsDTO = articleCmdBizService.recoverCategory(categoryRecoverParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("/category/queryByPage")
    public ResultView<PageResult<CategoryPageListDTO>> queryCategoryByPage(
            @Validated @RequestBody PageQry<CategoryQueryParam> categoryQueryParamPageQry
    ) {
        PageResult<CategoryPageListDTO> result = articleQueryBizService.queryCategoryByPage(categoryQueryParamPageQry);
        return ResultView.success(result);
    }

    @GetMapping("/category/queryById/{categoryId}")
    public ResultView<CategoryDTO> queryCategoryById(
            @PathVariable("categoryId") Long categoryId
    ) {
        CategoryDTO result = articleQueryBizService.queryCategoryById(categoryId);
        return ResultView.success(result);
    }

}
