package top.ddgotxdy.auth.model;

import lombok.Data;

import java.util.List;

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
     * 当前密码
     */
    private String currentPassword;

    /**
     * 重复新密码
     */
    private String rePassword;

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
    /**
     * 验证码
     */
    private String captcha;
    /**
     * 用户 id 列表
     */
    private List<Long> userIds;
    /**
     * 角色 id 列表
     */
    private List<Long> roleIds;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 包含的权限，例如[1001,1002,1003]
     */
    private List<Long> menuIds;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 父亲id
     */
    private Long parentId;

    /**
     * 菜单备注
     */
    private String menuDesc;

    /**
     * 资源ID
     */
    private Long resourceId;

    /**
     * 资源ID列表
     */
    private List<Long> resourceIds;

    /**
     * 资源名
     */
    private String resourceName;

    /**
     * 请求uri地址
     */
    private String uri;

    /**
     * 备注
     */
    private String resourceDesc;
}
