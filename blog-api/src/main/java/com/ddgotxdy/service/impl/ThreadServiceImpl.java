package com.ddgotxdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ddgotxdy.entity.Article;
import com.ddgotxdy.mapper.ArticleMapper;
import com.ddgotxdy.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: ddgo
 * @description: 线程池服务具体实现类
 */
@Async("taskExecutor")
@Service
public class ThreadServiceImpl implements ThreadService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void updateViewCount(Article article) {
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(article.getViewCounts() + 1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId,article.getId());
        queryWrapper.eq(Article::getViewCounts, article.getViewCounts());
        articleMapper.update(articleUpdate,queryWrapper);
    }

    @Override
    public void updateCommentCount(Article article) {
        Article articleUpdate = new Article();
        articleUpdate.setCommentCounts(article.getCommentCounts() + 1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId,article.getId());
        queryWrapper.eq(Article::getCommentCounts, article.getCommentCounts());
        articleMapper.update(articleUpdate,queryWrapper);
    }
}
