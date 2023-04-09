package top.ddgotxdy.article.service;

import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.article.addparam.ArticleAddParam;
import top.ddgotxdy.common.model.article.addparam.CategoryAddParam;
import top.ddgotxdy.common.model.article.addparam.TagAddParam;

/**
 * @author: ddgo
 * @description: 文章服务biz层，controller <--> service转换
 */
public interface ArticleBizService {
    /**
     * 添加文章
     * @param articleAddParam 添加文章请求参数
     * @return 返回 IdDTO
     */
    IdDTO addArticle(ArticleAddParam articleAddParam);

    /**
     * 添加标签
     * @param tagAddParam 添加标签请求参数
     * @return IdDTO
     */
    IdDTO addTag(TagAddParam tagAddParam);

    /**
     * 添加分类
     * @param categoryAddParam 添加分类请求参数
     * @return IdDTO
     */
    IdDTO addCategory(CategoryAddParam categoryAddParam);
}
