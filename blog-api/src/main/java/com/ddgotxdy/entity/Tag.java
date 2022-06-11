package com.ddgotxdy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author: ddgo
 * @description: 标签实体类
 */
@Data
public class Tag {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String avatar;

    private String tagName;

}
