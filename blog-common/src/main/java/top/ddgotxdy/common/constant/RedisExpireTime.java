package top.ddgotxdy.common.constant;

/**
 * @author: ddgo
 * @description:
 */
public class RedisExpireTime {
    private RedisExpireTime() {}

    /**
     * 验证码两分支过期
     */
    public static final Integer CAPTCHA_EXPIRE_SECOND = 60 * 2;
}
