package top.ddgotxdy.sms.model;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description: 消息封装对象
 */
@Data
public class EmailModel {

    /**
     * 发送邮箱列表
     */
    private List<String> tos;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;
}