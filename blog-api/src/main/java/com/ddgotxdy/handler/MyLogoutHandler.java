package com.ddgotxdy.handler;

import com.alibaba.fastjson.JSON;
import com.ddgotxdy.constant.RedisPrefix;
import com.ddgotxdy.entity.LoginUser;
import com.ddgotxdy.util.RedisCache;
import com.ddgotxdy.util.WebUtil;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ddgo
 * @description: 登出处理
 */
@Component
public class MyLogoutHandler implements LogoutHandler {

    @Autowired
    private RedisCache redisCache;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {

        // 获取authentication中的用户id
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        // 删除redis中的值
        redisCache.deleteObject(RedisPrefix.LOGIN + userId);
        // 返回结果
        Result result = Result.success("注销成功");
        String json = JSON.toJSONString(result);
        WebUtil.renderString(response, json);
    }
}
