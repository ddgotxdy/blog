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
    /**
     * 角色添加
     */
    ROLE_ADD(9),
    /**
     * 角色更新
     */
    ROLE_UPDATE(10),
    /**
     * 角色删除
     */
    ROLE_DELETE(11),
    /**
     * 角色恢复
     */
    ROLE_RECOVER(12),
    /**
     * 菜单添加
     */
    MENU_ADD(13),
    /**
     * 菜单更新
     */
    MENU_UPDATE(14),
    /**
     * 菜单删除
     */
    MENU_DELETE(15),
    /**
     * 菜单恢复
     */
    MENU_RECOVER(16),
    /**
     * 资源添加
     */
    RESOURCE_ADD(17),
    /**
     * 资源更新
     */
    RESOURCE_UPDATE(18),
    /**
     * 资源删除
     */
    RESOURCE_DELETE(19),
    /**
     * 资源恢复
     */
    RESOURCE_RECOVER(20),
    ;
    private final int code;
}
