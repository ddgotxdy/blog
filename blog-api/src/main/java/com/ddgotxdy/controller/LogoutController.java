package com.ddgotxdy.controller;

import com.ddgotxdy.service.LogoutService;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description: 登出控制器
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private LogoutService logoutService;

    @GetMapping
    public Result logout(){
        return logoutService.logout();
    }
}