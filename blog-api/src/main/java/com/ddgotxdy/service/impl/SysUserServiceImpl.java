package com.ddgotxdy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ddgotxdy.entity.LoginUser;
import com.ddgotxdy.entity.SysUser;
import com.ddgotxdy.mapper.SysUserMapper;
import com.ddgotxdy.service.SysUserService;
import com.ddgotxdy.vo.LoginUserVO;
import com.ddgotxdy.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public Result getUserInfoByToken() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginUserVO loginUserVO = new LoginUserVO();
        loginUserVO.setAccount(loginUser.getUser().getAccount());
        loginUserVO.setAvatar(loginUser.getUser().getAvatar());
        loginUserVO.setId(loginUser.getUser().getId());
        loginUserVO.setNickname(loginUser.getUser().getNickname());
        return Result.success(loginUserVO);
    }

    @Override
    public Result findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account);
        return Result.success(sysUserMapper.selectOne(queryWrapper));
    }

    @Override
    public Result save(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
        return Result.success(null);
    }


}
