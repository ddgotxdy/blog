package com.ddgotxdy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: ddgo
 * @description: 登录参数
 */
@Data
@AllArgsConstructor
public class LoginParam {

    private String account;

    private String password;
}