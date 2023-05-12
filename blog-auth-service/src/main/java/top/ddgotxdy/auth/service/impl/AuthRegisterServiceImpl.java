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
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.REGISTER)
@Service
@Slf4j
public class AuthRegisterServiceImpl extends AbstractAuthService {

    @Resource
    private BlogUserService blogUserService;
    @Resource
    private PasswordEncoder passwordEncoder;

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