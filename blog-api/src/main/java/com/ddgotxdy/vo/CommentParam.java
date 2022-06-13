package com.ddgotxdy.vo;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 评论参数
 */
@Data
public class CommentParam {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}