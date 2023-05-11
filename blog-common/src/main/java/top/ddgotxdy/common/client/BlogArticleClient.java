package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.deleteparam.ArticleBodyDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.CategoryDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.TagDeleteParam;
import top.ddgotxdy.common.model.article.dto.ArticleBodyPageListDTO;
import top.ddgotxdy.common.model.article.dto.CategoryPageListDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.model.article.queryparam.ArticleBodyQueryParam;
import top.ddgotxdy.common.model.article.queryparam.CategoryQueryParam;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.common.model.article.recoverparam.ArticleBodyRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.CategoryRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.TagRecoverParam;
import top.ddgotxdy.common.model.article.updateparam.ArticleBodyUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;

/**
 * @author: ddgo
 * @description: 博客服务对外接口
 */
@Component
@FeignClient("article-service")
public interface BlogArticleClient {

    /**
     * 分页查询文章
     * @param articleBodyQueryParamPageQry 分页查询文章参数
     * @return ResultView<PageResult<ArticleBodyPageListDTO>>
     */
    @PostMapping("openfeign/article/body/queryByPage")
    ResultView<PageResult<ArticleBodyPageListDTO>> queryArticleBodyByPage(
            @Validated @RequestBody PageQry<ArticleBodyQueryParam> articleBodyQueryParamPageQry
    );

    /**
     * 添加文章
     * @param addArticleParam 添加文章请求参数
     * @return 创建文章的id值
     */
    @PostMapping("openfeign/article/body/add")
    ResultView<IdDTO> addArticleBody(
            @Validated @RequestBody ArticleBodyAddParam addArticleParam
    );

    /**
     * 更新文章
     * @param articleBodyUpdateParam 更新文章请求参数
     * @return 更新文章的id
     */
    @PostMapping("openfeign/article/body/update")
    ResultView<IdDTO> updateArticleBody(
            @Validated @RequestBody ArticleBodyUpdateParam articleBodyUpdateParam
    );

    /**
     * 删除文章
     * @param articleBodyDeleteParam 删除文章参数
     * @return ResultView<IdsDTO>
     */
    @DeleteMapping("openfeign/article/body/delete")
    ResultView<IdsDTO> deleteArticleBody(
            @Validated @RequestBody ArticleBodyDeleteParam articleBodyDeleteParam
    );

    /**
     * 恢复文章
     * @param articleBodyRecoverParam 恢复文章参数
     * @return ResultView<IdsDTO>
     */
    @PostMapping("body/recover")
    ResultView<IdsDTO> recoverArticleBody(
            @Validated @RequestBody ArticleBodyRecoverParam articleBodyRecoverParam
    );

    /**
     * 添加标签
     * @param tagAddParam 标签添加参数
     * @return 创建标签的id值
     */
    @PostMapping("openfeign/article/tag/add")
    ResultView<IdDTO> addTag(
            @Validated @RequestBody TagAddParam tagAddParam
    );

    /**
     * 更新标签
     * @param tagUpdateParam 标签更新参数
     * @return 更新标签的id值
     */
    @PostMapping("openfeign/article/tag/update")
    ResultView<IdDTO> updateTag(
            @Validated @RequestBody TagUpdateParam tagUpdateParam
    );

    /**
     * 标签删除
     * @param tagDeleteParam 标签删除参数
     * @return ResultView<IdsDTO>
     */
    @DeleteMapping("openfeign/article/tag/delete")
    ResultView<IdsDTO> deleteTag(
            @RequestBody TagDeleteParam tagDeleteParam
    );

    /**
     * 标签恢复接口
     * @param tagRecoverParam 标签恢复参数
     * @return ResultView<IdsDTO>
     */
    @PostMapping("openfeign/article/tag/recover")
    ResultView<IdsDTO> recoverTag(
            @RequestBody TagRecoverParam tagRecoverParam
    );

    /**
     * 添加分类
     * @param categoryAddParam 分类添加参数
     * @return 创建分类的id值
     */
    @PostMapping("openfeign/article/category/add")
    ResultView<IdDTO> addCategory(
            @Validated @RequestBody CategoryAddParam categoryAddParam
    );

    /**
     * 更新分类
     * @param categoryUpdateParam 分类更新参数
     * @return 更新分类的id值
     */
    @PostMapping("openfeign/article/category/update")
    ResultView<IdDTO> updateCategory(
            @Validated @RequestBody CategoryUpdateParam categoryUpdateParam
    );

    /**
     * 删除分类
     * @param categoryDeleteParam 分类删除参数
     * @return 没有被删除的分类列表
     */
    @DeleteMapping("openfeign/article/category/delete")
    ResultView<IdsDTO> deleteCategory(
            @RequestBody CategoryDeleteParam categoryDeleteParam
    );

    /**
     * 恢复分类
     * @param categoryRecoverParam 恢复分类参数
     * @return 没有被恢复的分类列表
     */
    @PostMapping("openfeign/article/category/recover")
    ResultView<IdsDTO> recoverCategory(
            @RequestBody CategoryRecoverParam categoryRecoverParam
    );

    //---------------------------------查询接口----------------------------------------//

    /**
     * 分页查询标签
     * @param tagQueryParamPageQry 分页查询标签参数
     * @return 标签分页结果对象
     */
    @PostMapping("openfeign/article/tag/queryByPage")
    ResultView<PageResult<TagPageListDTO>> queryTagByPage(
            @Validated @RequestBody PageQry<TagQueryParam> tagQueryParamPageQry
    );

    /**
     * 分页查询分类
     * @param categoryQueryParamPageQry 分页查询分类参数
     * @return 分类分页结果对象
     */
    @PostMapping("openfeign/article/category/queryByPage")
    ResultView<PageResult<CategoryPageListDTO>> queryCategoryByPage(
            @Validated @RequestBody PageQry<CategoryQueryParam> categoryQueryParamPageQry
    );

}
