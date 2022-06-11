package com.ddgotxdy.service;

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
}
