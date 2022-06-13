package com.ddgotxdy.handler;

import com.alibaba.fastjson.JSON;
import com.ddgotxdy.constant.ErrorCode;
import com.ddgotxdy.util.WebUtil;
import com.ddgotxdy.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author ddgo
 * @description: 认证失败处理器
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
        String json = JSON.toJSONString(result);
        WebUtil.renderString(response, json);
    }
}