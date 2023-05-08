package top.ddgotxdy.common.model.sms.updateparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class SensitiveUpdateParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 需要更新的id
     */
    private Long sensitiveId;
    /**
     * 敏感词
     */
    private String word;
    /**
     * 敏感类型
     */
    private Integer sensitiveType;
}
