package top.ddgotxdy.common.constant;

/**
 * @author: ddgo
 * @description:
 */
public class RedisExpireTime {
    private RedisExpireTime() {}

    /**
     * 验证码两分钟过期
     */
    public static final Integer CAPTCHA_EXPIRE_SECOND = 60 * 2;

    /**
     * token redis 里面两小时过期
     */
    public static final Integer TOKEN_EXPIRE_SECOND = 2 * 60 * 60;
}
