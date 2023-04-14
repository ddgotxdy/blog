package top.ddgotxdy.article.service;

import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.article.addparam.ArticleBodyAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;
import top.ddgotxdy.common.model.article.updateparam.ArticleBodyUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.CategoryUpdateParam;
import top.ddgotxdy.common.model.article.updateparam.TagUpdateParam;

/**
 * @author: ddgo
 * @description: 文章服务biz层，controller <--> service转换
 */
public interface ArticleBizService {
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
}
