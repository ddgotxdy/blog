package com.ddgotxdy.service;

import com.ddgotxdy.vo.ArticleParam;
import com.ddgotxdy.vo.PageParams;
import com.ddgotxdy.vo.Result;

/**
 * @author: ddgo
 * @description: 文章服务接口
 */
public interface ArticleService {

    /**
     * 分页查询，文章列表
     * @param pageParams 分页查询阐述
     * @return Article
     */
    Result listArticle(PageParams pageParams);

    /**
     * 返回最热文章
     * @param limit 前limit个
     * @return Result
     */
    Result hotArticle(int limit);

    /**
     * 返回最新文章
     * @param limit 前limit个文章
     * @return Result
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @return Result
     */
    Result listArchives();

    /**
     * 根据博客id返回博客
     * @param id 博客id
     * @return Result
     */
    Result findArticleById(Long id);

    /**
     * 根据博客id返回博客内容
     * @param id 博客id
     * @return Result
     */
    Result findArticleBodyById(Long id);

    /**
     * 发布文章
     * @param articleParam 发布文章需要的参数
     * @return Result
     */
    Result publish(ArticleParam articleParam);
}
