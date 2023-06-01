package top.ddgotxdy.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.dal.entity.*;
import top.ddgotxdy.dal.mapper.*;

import javax.annotation.Resource;
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
    @Resource
    private BlogRoleMenuMapper blogRoleMenuMapper;
    @Resource
    private BlogRoleResourceMapper blogRoleResourceMapper;
    @Resource
    private BlogResourceMapper blogResourceMapper;

    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {
        LoginUser loginUser = new LoginUser();
        // 查询用户信息
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        // 可以根据用户名或者邮箱进行登录
        queryWrapper
                .eq(BlogUser::getIsDelete, false)
                .and(qw ->
                        qw.eq(BlogUser::getUsername, accountName)
                        .or()
                        .eq(BlogUser::getEmail, accountName));
        BlogUser blogUser = blogUserMapper.selectOne(queryWrapper);
        // 如果没有查询到用户，就抛出异常
        if(Objects.isNull(blogUser)) {
            throw new BlogException(ResultCode.LOGIN_ERROR);
        }
        loginUser.setUser(blogUser);
        // 获取角色表
        Long roleId = blogUser.getRoleId();
        BlogRole blogRole = blogRoleMapper.selectById(roleId);
        if(Objects.isNull(blogRole) || blogRole.getIsDelete()) {
            return loginUser;
        }
        // 查询对应的路由信息
        LambdaQueryWrapper<BlogRoleMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(BlogRoleMenu::getRoleId, roleId);
        List<BlogRoleMenu> roleMenuList = blogRoleMenuMapper.selectList(lambdaQueryWrapper);
        List<Long> menuIdList = roleMenuList.stream()
                .map(BlogRoleMenu::getMenuId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(menuIdList)) {
            List<BlogMenu> blogMenus = blogMenuMapper.selectBatchIds(menuIdList);
            // 路由去重
            List<String> paths = blogMenus.stream()
                    .filter(blogMenu -> !blogMenu.getIsDelete())
                    .map(BlogMenu::getPath)
                    .distinct()
                    .collect(Collectors.toList());
            loginUser.setPaths(paths);
        }
        // 查询对应的权限信息
        LambdaQueryWrapper<BlogRoleResource> resourceLambdaQueryWrapper = new LambdaQueryWrapper<>();
        resourceLambdaQueryWrapper
                .eq(BlogRoleResource::getRoleId, roleId);
        List<BlogRoleResource> roleResourceList = blogRoleResourceMapper.selectList(resourceLambdaQueryWrapper);
        List<Long> resourceIdList = roleResourceList.stream()
                .map(BlogRoleResource::getResourceId)
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(resourceIdList)) {
            List<BlogResource> blogResources = blogResourceMapper.selectBatchIds(resourceIdList);
            // path去重
            List<String> uris = blogResources.stream()
                    .filter(blogResource -> !blogResource.getIsDelete())
                    .map(BlogResource::getUri)
                    .distinct()
                    .collect(Collectors.toList());
            loginUser.setUris(uris);
        }
        return loginUser;
    }
}