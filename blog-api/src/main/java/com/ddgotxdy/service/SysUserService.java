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

    /**
     * 返回用户信息
     * @return Result
     */
    Result getUserInfoByToken();

    /**
     * 通过账户查找用户
     * @param account 账户
     * @return SysUser
     */
    Result findUserByAccount(String account);

    /**
     * 保存用户
     * @param sysUser 具体用户
     * @return Result
     */
    Result save(SysUser sysUser);
}
