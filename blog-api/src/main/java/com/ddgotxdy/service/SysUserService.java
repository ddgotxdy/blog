package com.ddgotxdy.service;

import com.ddgotxdy.entity.SysUser;
import com.ddgotxdy.vo.Result;

/**
 * @author: ddgo
 * @description:
 */
public interface SysUserService {

    /**
     * 通过用户id查找用户
     * @param id 用户id
     * @return Result
     */
    Result findUserById(Long id);

}
