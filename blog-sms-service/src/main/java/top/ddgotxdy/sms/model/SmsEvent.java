package top.ddgotxdy.sms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: ddgo
 * @description: 消息事件
 */
@Getter
@AllArgsConstructor
public enum SmsEvent {
    /**
     * 敏感词新增
     */
    SENSITIVE_ADD(1),
    /**
     * 敏感词更新
     */
    SENSITIVE_UPDATE(2),
    /**
     * 敏感词删除
     */
    SENSITIVE_DELETED(3),
    /**
     * 敏感词恢复
     */
    SENSITIVE_RECOVER(4),
    /**
     * 留言新增
     */
    MESSAGE_ADD(5),
    /**
     * 留言更新
     */
    MESSAGE_UPDATE(6),
    /**
     * 评论新增
     */
    COMMENT_ADD(7),
    /**
     * 评论更新
     */
    COMMENT_UPDATE(8),
    /**
     * 评论删除
     */
    COMMENT_DELETED(9),
    /**
     * 评论恢复
     */
    COMMENT_RECOVER(10),
    ;
    private final int code;
}
