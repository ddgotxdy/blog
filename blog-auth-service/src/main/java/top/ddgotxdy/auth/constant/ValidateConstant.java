package top.ddgotxdy.auth.constant;

/**
 * @author: ddgo
 * @description:
 */
public class ValidateConstant {
    private ValidateConstant() { }

    /**
     * 用户名的最大长度
     */
    public static final Integer USERNAME_MAX_LENGTH = 20;
    /**
     * 用户名的最小长度
     */
    public static final Integer USERNAME_MIN_LENGTH = 6;
    /**
     * 用户真实名称的最大长度
     */
    public static final Integer NICKNAME_MAX_LENGTH = 20;
    /**
     * 用户真实名称的最小长度
     */
    public static final Integer NICKNAME_MIN_LENGTH = 2;
    /**
     * 手机号长度
     */
    public static final Integer PHONE_NUMBER_LENGTH = 11;
    /**
     * 头像链接长度
     */
    public static final Integer AVATAR_URL_MAX_LENGTH = 128;
    /**
     * 密码的最大长度
     */
    public static final Integer PASSWORD_MAX_LENGTH = 50;
    /**
     * 密码的最小长度
     */
    public static final Integer PASSWORD_MIN_LENGTH = 6;
    /**
     * 描述的最大长度
     */
    public static final Integer DESC_MAX_LENGTH = 512;
    /**
     * 角色名的最大长度
     */
    public static final Integer ROLE_NAME_MAX_LENGTH = 10;
    /**
     * 角色名的最小长度
     */
    public static final Integer ROLE_NAME_MIN_LENGTH = 2;
}
