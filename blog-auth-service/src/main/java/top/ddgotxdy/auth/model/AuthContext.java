package top.ddgotxdy.auth.model;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class AuthContext {
    /**
     * 对应事件
     */
    AuthEvent authEvent;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 用户真实昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户性别（0男，1女，2未知）
     */
    private Integer sex;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 是否删除，0否1是
     */
    private Boolean isDelete;
}
