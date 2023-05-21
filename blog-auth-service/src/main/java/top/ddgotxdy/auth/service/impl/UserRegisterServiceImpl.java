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
@AuthEventSelector(AuthEvent.USER_REGISTER)
@Service
@Slf4j
public class UserRegisterServiceImpl extends AbstractAuthService {
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private BlogSmsClient blogSmsClient;

    @Override
    protected boolean filter(AuthContext authContext) {
        if (Objects.isNull(authContext)) {
            throw new BlogException(ResultCode.REGISTER_ERROR);
        }
        // 1. 用户名唯一
        if (!uniqueUsername(authContext)) {
            throw new BlogException(ResultCode.REGISTER_ERROR.getCode(), "用户名存在");
        }
        // 2. 邮箱唯一
        if (!uniqueEmail(authContext)) {
            throw new BlogException(ResultCode.REGISTER_ERROR.getCode(), "邮箱存在");
        }
        // 3. 密码相等
        String password = authContext.getPassword();
        String rePassword = authContext.getRePassword();
        if (!Objects.equals(password, rePassword)) {
            throw new BlogException(ResultCode.USER_PASSWORD_UPDATE_ERROR.getCode(), "新密码不相等");
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
        BlogUser blogUser = Context2EntityConvert.authContext2UserForAdd(authContext);
        // 密码加密
        blogUser.setPassword(passwordEncoder.encode(authContext.getPassword()));
        blogUserService.save(blogUser);
        authContext.setUserId(blogUser.getUserId());
    }
}
