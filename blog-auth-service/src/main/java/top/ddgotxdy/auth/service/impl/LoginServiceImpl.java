package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.service.LoginService;
import top.ddgotxdy.common.constant.RedisPrefix;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.util.JwtUtil;
import top.ddgotxdy.common.util.RedisCache;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static top.ddgotxdy.common.constant.RedisExpireTime.TOKEN_EXPIRE_SECOND;

/**
 * @author: ddgo
 * @description:
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    private RedisCache redisCache;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(UserLoginModel userLoginModel) {
        LoginUser loginUser = (LoginUser) userDetailsService.loadUserByUsername(userLoginModel.getUsername());
        // 判断账号密码是否正确
        String password = loginUser.getUser().getPassword();
        if (!passwordEncoder.matches(userLoginModel.getPassword(), password)) {
            throw new BlogException(ResultCode.LOGIN_ERROR);
        }
        // 如果认证通过了，使用userid生成一个jwt
        String userId = loginUser.getUser().getUserId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 把完整的用户信息存入redis  userid作为key
        redisCache.setCacheObject(
                RedisPrefix.LOGIN + userId,
                loginUser,
                TOKEN_EXPIRE_SECOND,
                TimeUnit.SECONDS);
        return jwt;
    }

    @Override
    public void logout() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        // 删除redis中的值
        redisCache.deleteObject(RedisPrefix.LOGIN + userId);
    }
}
