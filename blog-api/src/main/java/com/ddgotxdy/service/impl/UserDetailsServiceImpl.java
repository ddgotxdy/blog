package com.ddgotxdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ddgotxdy.entity.LoginUser;
import com.ddgotxdy.entity.SysUser;
import com.ddgotxdy.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author: ddgo
 * @description: UserDetailsService实现类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        // 如果没有查询到用户，就抛出异常
        if(Objects.isNull(sysUser)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        // 查询对应的权限信息
//        List<String> permissions = menuMapper.selectPermsByUserId(securityUser.getId());
        // 封装成UserDetails
        return new LoginUser(sysUser, null);
    }
}
