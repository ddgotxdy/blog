package top.ddgotxdy.common.constant;

/**
 * @author: ddgo
 * @description: 白名单常量
 */
public class WhiteListConstant {
    private WhiteListConstant() { }

    /**
     * 邮箱白名单常量，只用于注册，不可用于登录
     */
    public static final String EMAIL_WHITE = "test@qq.com";

    /**
     * 当邮箱为白名单时，输入当前验证码放行
     */
    public static final String CAPTCHA_WHITE = "123456";
}
