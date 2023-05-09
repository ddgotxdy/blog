package top.ddgotxdy.article.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ddgotxdy.article.service.ArticleCmdBizService;
import top.ddgotxdy.article.service.ArticleQueryBizService;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description: 网站其它接口
 */
@RestController
@RequestMapping("openfeign/website")
public class WebsiteController {
    @Resource
    private ArticleCmdBizService articleCmdBizService;
    @Resource
    private ArticleQueryBizService articleQueryBizService;

//    @PostMapping("/message/queryByPage")
//    public queryMessageByPage(
//
//    ) {
//
//    }
}
