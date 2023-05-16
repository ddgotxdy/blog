package top.ddgotxdy.common.enums.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    /**
     * 未知类型
     */
    UNKNOWN(0, "未知类型"),
    /**
     * 男
     */
    MAN(1, "男"),
    /**
     * 私密，管理员可见
     */
    WOMAN(2, "女"),
    ;
    private final Integer code;
    private final String msg;

    @JsonValue
    public int getCode() {
        return this.code;
    }

    @JsonCreator
    public static SexEnum of(Integer code) {
        if (Objects.isNull(code)) {
            return SexEnum.UNKNOWN;
        }
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.getCode() == code) {
                return sexEnum;
            }
        }
        return SexEnum.UNKNOWN;
    }
}
