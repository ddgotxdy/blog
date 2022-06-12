package com.ddgotxdy.service;

import com.ddgotxdy.vo.Result;

/**
 * @author: ddgo
 * @description: 登出请求服务
 */
public interface LogoutService {
    /**
     * 用户退出
     * @return Result
     */
    Result logout();
}
