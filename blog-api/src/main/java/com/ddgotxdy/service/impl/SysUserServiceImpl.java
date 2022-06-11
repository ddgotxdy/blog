package com.ddgotxdy.service.impl;

import com.ddgotxdy.entity.SysUser;
import com.ddgotxdy.mapper.SysUserMapper;
import com.ddgotxdy.service.SysUserService;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Result findUserById(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if(sysUser == null) {
            sysUser = new SysUser();
            sysUser.setNickname("ddgo");
        }
        return Result.success(sysUser);
    }
}
