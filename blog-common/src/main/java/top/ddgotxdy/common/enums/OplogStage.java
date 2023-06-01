package top.ddgotxdy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Getter
@AllArgsConstructor
public enum OplogStage {
    /**
     * 未知
     */
    UNKNOWN_OPLOG(0),
    /**
     * 预操作
     */
    PRE_OPLOG(1),
    /**
     * 落库操作
     */
    AFTER_OPLOG(2),
    ;
    private final Integer code;
    public static OplogStage of(Integer code) {
        for (OplogStage oplogStage : OplogStage.values()) {
            if (Objects.equals(oplogStage.getCode(), code)) {
                return oplogStage;
            }
        }
        return UNKNOWN_OPLOG;
    }
}
