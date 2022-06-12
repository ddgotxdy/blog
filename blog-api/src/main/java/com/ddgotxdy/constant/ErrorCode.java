package com.ddgotxdy.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * @author: ddgo
 * @description: 定义的状态码
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

    PARAMS_ERROR(10001,"参数有误"),
    ACCOUNT_PWD_NOT_EXIST(10002,"用户名或密码不存在"),
    NO_PERMISSION(70001,"无访问权限"),
    SESSION_TIME_OUT(90001,"会话超时"),
    NO_LOGIN(90002,"未登录"),
    ACCOUNT_EXIST(10004,"账号已存在"),;

    private int code;
    private String msg;
}
