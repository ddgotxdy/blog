package com.ddgotxdy.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author: ddgo
 * @description: 分类实体
 */
@Data
public class Category {

    @TableId
    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}