package top.ddgotxdy.common.model.sms.addparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class SensitiveAddParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 敏感词
     */
    private String word;
    /**
     * 敏感类型
     */
    private Integer sensitiveType;
}