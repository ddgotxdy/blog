package top.ddgotxdy.common.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;
import top.ddgotxdy.dal.mapper.BlogMenuMapper;
import top.ddgotxdy.dal.mapper.BlogRoleMapper;
import top.ddgotxdy.dal.mapper.BlogUserMapper;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: ddgo
 * @description: UserDetailsService的实现类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private BlogUserMapper blogUserMapper;

    @Resource
    private BlogRoleMapper blogRoleMapper;

    @Resource
    private BlogMenuMapper blogMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BlogUser::getUsername, username);
        BlogUser blogUser = blogUserMapper.selectOne(queryWrapper);
        // 如果没有查询到用户，就抛出异常
        if(Objects.isNull(blogUser)) {
            throw new BlogException(ResultCode.LOGIN_ERROR.getCode(), ResultCode.LOGIN_ERROR.getMsg());
        }
        // 获取角色表
        Long roleId = blogUser.getRoleId();
        BlogRole blogRole = blogRoleMapper.selectById(roleId);
        // 如果没有查询到角色，就返回默认角色权限
        if(Objects.isNull(blogRole)) {
            List<String> permissionDefault = Collections.singletonList("user");
            return new LoginUser(blogUser, permissionDefault);
        }
        // 查询对应的权限信息
        String menuIds = blogRole.getMenuIds();
        List<Long> menuIdList = JSON.parseArray(menuIds, Long.class);
        List<BlogMenu> blogMenus = blogMenuMapper.selectBatchIds(menuIdList);
        // 去重
        List<String> permissions = blogMenus.stream()
                .map(BlogMenu::getPerms)
                .distinct()
                .collect(Collectors.toList());
        // 封装成UserDetails
        return new LoginUser(blogUser, permissions);
    }
}