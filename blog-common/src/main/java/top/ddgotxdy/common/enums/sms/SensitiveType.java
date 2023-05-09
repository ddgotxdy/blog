package top.ddgotxdy.common.enums.sms;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: ddgo
 * @description:
 */
@Getter
@AllArgsConstructor
public enum SensitiveType {
    /**
     * 未知类型
     */
    SENSITIVE_UNKNOWN(0, "未知类型"),
    /**
     * 敏感词通过
     */
    SENSITIVE_ALLOW(1, "敏感词通过"),
    /**
     * 敏感词不通过
     */
    SENSITIVE_DENY(2, "敏感词不通过"),
    ;
    private final Integer code;
    private final String msg;

    @JsonValue
    public int getCode() {
        return this.code;
    }

    @JsonCreator
    public static SensitiveType of(int code) {
        for (SensitiveType sensitiveType : SensitiveType.values()) {
            if (sensitiveType.getCode() == code) {
                return sensitiveType;
            }
        }
        return SensitiveType.SENSITIVE_UNKNOWN;
    }
}
