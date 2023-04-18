package top.ddgotxdy.article.service;

import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdsDTO;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.deleteparam.CategoryDeleteParam;
import top.ddgotxdy.common.model.article.deleteparam.TagDeleteParam;
import top.ddgotxdy.common.model.article.dto.TagDTO;
import top.ddgotxdy.common.model.article.dto.TagPageListDTO;
import top.ddgotxdy.common.model.article.queryparam.TagQueryParam;
import top.ddgotxdy.common.model.article.recoverparam.CategoryRecoverParam;
import top.ddgotxdy.common.model.article.recoverparam.TagRecoverParam;
import top.ddgotxdy.common.model.article.updateparam.ArticleBodyUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;

/**
 * @author: ddgo
 * @description: 文章服务biz层，组装cud操作
 */
public interface ArticleCmdBizService {
    /**
     * 添加文章
     * @param articleBodyAddParam 添加文章请求参数
     * @return 返回 IdDTO
     */
    IdDTO addArticleBody(ArticleBodyAddParam articleBodyAddParam);

    /**
     * 更新文章
     * @param articleBodyUpdateParam 文章更新参数
     * @return IdDTO
     */
    IdDTO updateArticleBody(ArticleBodyUpdateParam articleBodyUpdateParam);

    /**
     * 添加标签
     * @param tagAddParam 添加标签请求参数
     * @return IdDTO
     */
    IdDTO addTag(TagAddParam tagAddParam);

    /**
     * 修改标签
     * @param tagUpdateParam 修改标签请求参数
     * @return IdDTO
     */
    IdDTO updateTag(TagUpdateParam tagUpdateParam);

    /**
     * 删除标签接口
     * @param tagDeleteParam 删除标签参数
     * @return 列表dto
     */
    IdsDTO deleteTag(TagDeleteParam tagDeleteParam);

    /**
     * 恢复标签接口
     * @param tagRecoverParam 恢复标签参数
     * @return 列表dto
     */
    IdsDTO recoverTag(TagRecoverParam tagRecoverParam);

    /**
     * 添加分类
     * @param categoryAddParam 添加分类请求参数
     * @return IdDTO
     */
    IdDTO addCategory(CategoryAddParam categoryAddParam);

    /**
     * 修改分类
     * @param categoryUpdateParam 更新分类请求参数
     * @return IdDTO
     */
    IdDTO updateCategory(CategoryUpdateParam categoryUpdateParam);

    /**
     * 删除分类
     * @param categoryDeleteParam 删除分类请求参数
     * @return 没有被删除的列表集合
     */
    IdsDTO deleteCategory(CategoryDeleteParam categoryDeleteParam);

    /**
     * 恢复分类
     * @param categoryRecoverParam 恢复分类请求参数
     * @return 没有被恢复的列表集合
     */
    IdsDTO recoverCategory(CategoryRecoverParam categoryRecoverParam);
}
