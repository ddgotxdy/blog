package com.ddgotxdy.controller;

import com.ddgotxdy.service.LoginService;
import com.ddgotxdy.vo.LoginParam;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description: 登录控制器类
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){

        return loginService.login(loginParam);
    }
}