package com.ddgotxdy.service.impl;

import com.ddgotxdy.constant.RedisPrefix;
import com.ddgotxdy.entity.LoginUser;
import com.ddgotxdy.service.LogoutService;
import com.ddgotxdy.util.RedisCache;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author: ddgo
 * @description: 登出实现类
 */
@Service
public class LogoutServiceImpl implements LogoutService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public Result logout() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 删除redis中的值
        redisCache.deleteObject(RedisPrefix.LOGIN + userId);
        return Result.success("注销成功");
    }
}
