package com.ddgotxdy.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description: 发布文章的参数
 */
@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVO category;

    private String summary;

    private List<TagVO> tags;

    private String title;
}
