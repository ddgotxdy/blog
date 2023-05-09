package top.ddgotxdy.common.model.sms.queryparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class SensitiveQueryParam {
    /**
     * 敏感词id
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
     * 是否删除
     */
    private Boolean isDelete;
}
