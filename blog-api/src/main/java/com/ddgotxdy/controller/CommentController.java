package com.ddgotxdy.controller;

import com.ddgotxdy.service.CommentService;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description: 评论控制器
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取文章的评论列表
     * @param articleId 文章id
     * @return Result
     */
    @GetMapping("/article/{id}")
    public Result comments(@PathVariable("id") Long articleId){
        return commentService.commentByArticleId(articleId);
    }
}