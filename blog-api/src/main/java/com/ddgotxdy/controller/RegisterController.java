package com.ddgotxdy.controller;

import com.ddgotxdy.service.RegisterService;
import com.ddgotxdy.vo.RegisterParam;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description: 注册控制器类
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping
    public Result register(@RequestBody RegisterParam registerParam){
        return registerService.register(registerParam);
    }
}