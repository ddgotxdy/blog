package com.ddgotxdy.entity;

import lombok.Data;


/**
 * @author: ddgo
 * @description: 文章body实体
 */
@Data
public class ArticleBody {

    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}