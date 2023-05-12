package top.ddgotxdy.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: ddgo
 * @description:
 */
@Getter
@AllArgsConstructor
public enum AuthEvent {
    /**
     * 注册
     */
    REGISTER(1),
    ;
    private final int code;
}
