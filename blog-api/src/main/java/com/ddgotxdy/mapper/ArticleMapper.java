package com.ddgotxdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ddgotxdy.dos.Archives;
import com.ddgotxdy.entity.Article;

import java.util.List;

/**
 * @author: ddgo
 * @description: ArticleMapper 接口
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 返回每年，每月，发的文章数量
     * @return Archives
     */
    List<Archives> listArchives();
}
