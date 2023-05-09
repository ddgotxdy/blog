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
public enum AuditType {
    /**
     * 未知类型
     */
    AUDIT_UNKNOWN(0, "未知类型"),
    /**
     * 审核中
     */
    AUDIT_ING(1, "审核中"),
    /**
     * 审核通过
     */
    AUDIT_PASS(2, "审核通过"),
    /**
     * 审核不通过
     */
    AUDIT_REJECT(3, "审核不通过"),
    ;
    private final Integer code;
    private final String msg;

    @JsonValue
    public int getCode() {
        return this.code;
    }

    @JsonCreator
    public static AuditType of(int code) {
        for (AuditType auditType : AuditType.values()) {
            if (auditType.getCode() == code) {
                return auditType;
            }
        }
        return AuditType.AUDIT_UNKNOWN;
    }
}
