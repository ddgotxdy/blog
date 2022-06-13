package com.ddgotxdy.service;

import com.ddgotxdy.entity.Article;

/**
 * @author: ddgo
 * @description: 线程服务类
 */
public interface ThreadService {

    /**
     * 线程池去更新阅读数量
     * @param article 具体待更新文章
     */
    void updateViewCount(Article article);

}
