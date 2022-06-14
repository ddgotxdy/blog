package com.ddgotxdy.entity;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 文章标签映射实体类
 */
@Data
public class ArticleTag {

    private Long id;

    private Long articleId;

    private Long tagId;
}