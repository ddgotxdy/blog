package top.ddgotxdy.common.model.sms.dto;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class SensitivePageListDTO {
    /**
     * 敏感ID
     */
    private Long sensitiveId;
    /**
     * 敏感词
     */
    private String word;

    /**
     * 敏感类型 2 deny 1 allow
     */
    private Integer sensitiveType;

    /**
     * 创建时间
     */
    private Long createTime;
}
