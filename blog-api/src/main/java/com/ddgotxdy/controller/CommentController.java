package com.ddgotxdy.controller;

import com.ddgotxdy.service.CommentService;
import com.ddgotxdy.vo.CommentParam;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加评论请求
     * @param commentParam 评论需要的参数
     * @return Result
     */
    @PostMapping("/create/change")
    public Result comment(@RequestBody CommentParam commentParam){
        return commentService.comment(commentParam);
    }

}