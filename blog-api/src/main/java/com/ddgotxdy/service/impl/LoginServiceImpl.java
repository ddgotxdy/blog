package com.ddgotxdy.service.impl;

import com.ddgotxdy.constant.RedisPrefix;
import com.ddgotxdy.entity.LoginUser;
import com.ddgotxdy.service.LoginService;
import com.ddgotxdy.util.JwtUtil;
import com.ddgotxdy.util.RedisCache;
import com.ddgotxdy.vo.LoginParam;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: ddgo
 * @description: 登录服务实现类
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public Result login(LoginParam loginParam) {
        // AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginParam.getAccount(),loginParam.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果认证没通过，给出对应的提示
        if(Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        // 如果认证通过了，使用userid生成一个jwt jwt存入Result返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        Map<String,String> map = new HashMap<>(16);
        map.put("token",jwt);
        // 把完整的用户信息存入redis  userid作为key
        redisCache.setCacheObject(RedisPrefix.LOGIN + userId, loginUser);
        return Result.success("登录成功", map);
    }
}
