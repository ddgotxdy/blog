package com.ddgotxdy.service.impl;

import com.ddgotxdy.constant.ErrorCode;
import com.ddgotxdy.constant.RedisPrefix;
import com.ddgotxdy.entity.SysUser;
import com.ddgotxdy.service.LoginService;
import com.ddgotxdy.service.RegisterService;
import com.ddgotxdy.service.SysUserService;
import com.ddgotxdy.util.JwtUtil;
import com.ddgotxdy.util.RedisCache;
import com.ddgotxdy.vo.LoginParam;
import com.ddgotxdy.vo.RegisterParam;
import com.ddgotxdy.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: ddgo
 * @description: 注册实现类
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private LoginService loginService;

    @Override
    public Result register(RegisterParam registerParam) {
        String account = registerParam.getAccount();
        String password = registerParam.getPassword();
        String nickname = registerParam.getNickname();
        // 参数校验
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
        ) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        // 判断账户是否存在
        Result result = this.sysUserService.findUserByAccount(account);
        if (result.getData() != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        // 构建用户
        SysUser sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(passwordEncoder.encode(password));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        //1 为true
        sysUser.setAdmin(1);
        // 0 为false
        sysUser.setDeleted(0);
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        // 保存用户
        result = sysUserService.save(sysUser);

        return loginService.login(new LoginParam(account, password));
    }
}
