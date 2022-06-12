package com.ddgotxdy.service;

import com.ddgotxdy.vo.LoginParam;
import com.ddgotxdy.vo.Result;

/**
 * @author: ddgo
 * @description: 登录服务接口
 */
public interface LoginService {
    /**
     * 登录
     * @param loginParam 登录请求参数
     * @return Result
     */
    Result login(LoginParam loginParam);
}