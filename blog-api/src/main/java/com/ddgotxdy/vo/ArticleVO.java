package com.ddgotxdy.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description: 文章返回给前端的类
 */
@Data
public class ArticleVO {

    private Long id;

    private String title;

    private String summary;

    private Integer commentCounts;

    private Integer viewCounts;

    private Integer weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    private ArticleBodyVO body;

    /**
     * 多个标签
     */
    private List<TagVO> tags;
    /**
     * 一个分类
     */
    private CategoryVO category;

}