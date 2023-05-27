package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.convert.Context2EntityConvert;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.auth.service.LoginService;
import top.ddgotxdy.common.client.BlogSmsClient;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.sms.queryparam.CaptchaQueryParam;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.USER_EMAIL_UPDATE)
@Service
@Slf4j
public class UserEmailUpdateServiceImpl extends AbstractAuthService {
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private BlogSmsClient blogSmsClient;
    @Resource
    private LoginService loginService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.USER_EMAIL_UPDATE_ERROR.getCode(), "用户id为空");
        }
        // 2. 当前密码正确性验证
        BlogUser blogUser = blogUserService.getById(userId);
        String blogUserPassword = blogUser.getPassword();
        String currentPassword = authContext.getCurrentPassword();
        if (!passwordEncoder.matches(currentPassword, blogUserPassword)) {
            throw new BlogException(ResultCode.USER_EMAIL_UPDATE_ERROR.getCode(), "密码错误");
        }
        // 3. 验证码验证
        String captcha = authContext.getCaptcha();
        String email = authContext.getEmail();
        CaptchaQueryParam captchaQueryParam = new CaptchaQueryParam();
        captchaQueryParam.setMail(email);
        ResultView<String> response = blogSmsClient.queryCaptcha(captchaQueryParam);
        String captchaFromRedis = response.checkAndGetData();
        if (!Objects.equals(captcha, captchaFromRedis)) {
            throw new BlogException(ResultCode.USER_EMAIL_UPDATE_ERROR.getCode(), "验证码错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogUser blogUser = Context2EntityConvert.authContext2UserForUpdate(authContext);
        blogUserService.updateById(blogUser);
        // 更新redis里面的信息
        loginService.refreshById(blogUser.getUserId());
    }
}
