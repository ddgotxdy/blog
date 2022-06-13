package com.ddgotxdy.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description: 评论视图数据
 */
@Data
public class CommentVO  {

    private Long id;

    private UserVO author;

    private String content;

    private List<CommentVO> childrens;

    private String createDate;

    private Integer level;

    private UserVO toUser;
}