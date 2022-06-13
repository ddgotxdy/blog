package com.ddgotxdy.service;

import com.ddgotxdy.vo.Result;

/**
 * @author: ddgo
 * @description: 评论服务接口
 */
public interface CommentService {

    /**
     * 根据文章id 查询评论
     * @param articleId 文章id
     * @return Result
     */
    Result commentByArticleId(Long articleId);
}