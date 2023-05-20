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
    USER_REGISTER(1),
    /**
     * 用户密码修改
     */
    USER_PASSWORD_UPDATE(2),
    /**
     * 用户邮箱信息修改
     */
    USER_EMAIL_UPDATE(4),
    /**
     * 用户个人信息修改
     */
    USER_INFO_UPDATE(5),
    /**
     * 用户角色修改
     */
    USER_ROLE_UPDATE(6),
    /**
     * 用户删除
     */
    USER_DELETE(7),
    /**
     * 用户恢复
     */
    USER_RECOVER(8),
    ;
    private final int code;
}
