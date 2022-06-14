package com.ddgotxdy.controller;

import com.ddgotxdy.service.ArticleService;
import com.ddgotxdy.vo.ArticleParam;
import com.ddgotxdy.vo.PageParams;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ddgo
 * @description: 文章控制器类
 */

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页文章列表，分页返回文章
     * @param pageParams 分页请求参数
     * @return Result
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams) {
        return articleService.listArticle(pageParams);
    }

    @PostMapping("/hot")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    /**
     * 首页 最新文章
     * @return Result
     */
    @PostMapping("/new")
    public Result newArticles(){
        int limit = 5;
        return articleService.newArticles(limit);
    }

    /**
     * 首页 文章归档
     * @return Result
     */
    @PostMapping("/listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }


    /**
     * 返回博客的具体内容
     * @param id 博客id
     * @return Result
     */
    @PostMapping("/view/{id}")
    public Result findArticleById(@PathVariable("id") Long id) {
        return articleService.findArticleById(id);
    }

    /**
     * 发布文章
     * @param articleParam 发布文章需要的参数
     * @return Result
     */
    @PostMapping("/publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }

}
