package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.convert.Context2EntityConvert;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.Objects;

import static top.ddgotxdy.auth.constant.ValidateConstant.PASSWORD_MAX_LENGTH;
import static top.ddgotxdy.auth.constant.ValidateConstant.PASSWORD_MIN_LENGTH;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.USER_PASSWORD_UPDATE)
@Service
@Slf4j
public class UserPasswordUpdateServiceImpl extends AbstractAuthService {
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.USER_PASSWORD_UPDATE_ERROR.getCode(), "用户id为空");
        }
        // 2. 当前密码正确性验证
        BlogUser blogUser = blogUserService.getById(userId);
        String blogUserPassword = blogUser.getPassword();
        String currentPassword = authContext.getCurrentPassword();
        if (!passwordEncoder.matches(currentPassword, blogUserPassword)) {
            throw new BlogException(ResultCode.USER_PASSWORD_UPDATE_ERROR.getCode(), "密码错误");
        }
        // 3. 密码相等
        String password = authContext.getPassword();
        String rePassword = authContext.getRePassword();
        if (!Objects.equals(password, rePassword)) {
            throw new BlogException(ResultCode.USER_PASSWORD_UPDATE_ERROR.getCode(), "新密码不相等");
        }
        // 4. 密码长度校验
        if (StringUtils.isNotEmpty(password)
                && (StringUtils.length(password) < PASSWORD_MIN_LENGTH
                || StringUtils.length(password) > PASSWORD_MAX_LENGTH)) {
            throw new BlogException(ResultCode.USER_PASSWORD_UPDATE_ERROR.getCode(), "密码长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogUser blogUser = Context2EntityConvert.authContext2UserForUpdate(authContext);
        // 密码加密
        blogUser.setPassword(passwordEncoder.encode(authContext.getPassword()));
        blogUserService.updateById(blogUser);
        // 更新redis里面的信息
        updateUserInfoFromRedis(authContext);
    }
}
