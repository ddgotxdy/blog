package com.ddgotxdy.vo;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 登录用户需要的信息
 */
@Data
public class LoginUserVO {
    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
