package top.ddgotxdy.sms.model;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description: 消息上下文
 */
@Data
public class SmsContext {
    /**
     * 对应事件
     */
    private SmsEvent smsEvent;
    /**
     * userId
     */
    private Long userId;
    /**
     * 敏感词id
     */
    private Long sensitiveId;
    /**
     * 敏感词
     */
    private String word;
    /**
     * 敏感词类型
     */
    private Integer sensitiveType;
    /**
     * 敏感词列表
     */
    private List<Long> sensitiveIds;
}
