package com.ddgotxdy.vo;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 分类的详细视图
 */
@Data
public class CategoryVO {

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}