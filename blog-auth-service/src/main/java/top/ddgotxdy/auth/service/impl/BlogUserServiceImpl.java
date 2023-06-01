package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.service.*;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.entity.BlogResource;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;
import top.ddgotxdy.dal.mapper.BlogUserMapper;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {
    @Resource
    private BlogRoleService blogRoleService;
    @Resource
    private BlogMenuService blogMenuService;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private BlogRoleMenuService blogRoleMenuService;
    @Resource
    private BlogRoleResourceService blogRoleResourceService;
    @Resource
    private BlogResourceService blogResourceService;

    @Override
    public boolean deleteById(Long userId) {
        LambdaUpdateWrapper<BlogUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogUser::getIsDelete, true)
                .in(BlogUser::getUserId, userId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverBatchByIds(List<Long> userIds) {
        LambdaUpdateWrapper<BlogUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogUser::getIsDelete, false)
                .in(BlogUser::getUserId, userIds);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverById(Long userId) {
        LambdaUpdateWrapper<BlogUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogUser::getIsDelete, false)
                .eq(BlogUser::getUserId, userId);
        return this.update(updateWrapper);
    }

    @Override
    public List<BlogUser> getByRoleId(Long roleId) {
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogUser::getIsDelete, false)
                .eq(BlogUser::getRoleId, roleId);
        return this.list(queryWrapper);
    }

    @Override
    public List<BlogUser> getByRoleIdAll(Long roleId) {
        if (Objects.isNull(roleId)) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogUser::getRoleId, roleId);
        return this.list(queryWrapper);
    }

    @Override
    public List<Long> queryByRoleIdList(List<Long> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogUser::getIsDelete, false)
                .in(BlogUser::getRoleId, roleIdList);
        List<BlogUser> blogUserList = this.list(queryWrapper);
        // 获取得到userId list
        List<Long> userIdList = blogUserList.stream()
                .map(BlogUser::getUserId)
                .collect(Collectors.toList());
        return userIdList;
    }

    @Override
    public LoginUser loadUserByUserId(Long userId) {
        LoginUser loginUser = new LoginUser();
        // 查询用户信息
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        // 可以根据用户id
        queryWrapper
                .eq(BlogUser::getIsDelete, false)
                .eq(BlogUser::getUserId, userId);
        List<BlogUser> blogUserList = this.list(queryWrapper);
        // 如果没有查询到用户，就抛出异常
        if(CollectionUtils.isEmpty(blogUserList)) {
            throw new BlogException(ResultCode.LOGIN_ERROR);
        }
        BlogUser blogUser = blogUserList.get(0);
        loginUser.setUser(blogUser);
        // 获取角色表
        Long roleId = blogUser.getRoleId();
        BlogRole blogRole = blogRoleService.getById(roleId);
        if(Objects.isNull(blogRole) || blogRole.getIsDelete()) {
            return loginUser;
        }
        // 查询对应的菜单信息
        List<Long> menuIdList = blogRoleMenuService.queryMenuIdListByRoleId(roleId);
        if (!CollectionUtils.isEmpty(menuIdList)) {
            List<BlogMenu> blogMenus = blogMenuService.listByIds(menuIdList);
            // 路由去重
            List<String> paths = blogMenus.stream()
                    .filter(blogMenu -> !blogMenu.getIsDelete())
                    .map(BlogMenu::getPath)
                    .distinct()
                    .collect(Collectors.toList());
            loginUser.setPaths(paths);
        }
        // 查询对应的资源信息
        List<Long> resourceIdList = blogRoleResourceService.queryResourceIdListByRoleId(roleId);
        if (!CollectionUtils.isEmpty(resourceIdList)) {
            List<BlogResource> blogResources = blogResourceService.listByIds(menuIdList);
            // 路由去重
            List<String> uris = blogResources.stream()
                    .filter(blogResource -> !blogResource.getIsDelete())
                    .map(BlogResource::getUri)
                    .distinct()
                    .collect(Collectors.toList());
            loginUser.setUris(uris);
        }
        return loginUser;
    }

    @Override
    public LoginUser loadUserByUsername(String username) {
        return (LoginUser) userDetailsService.loadUserByUsername(username);
    }

    @Override
    public List<BlogUser> getByMenuId(Long menuId) {
        List<Long> roleIdList = blogRoleMenuService.queryRoleIdListByMenuId(menuId);
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Collections.emptyList();
        }
        queryWrapper
                .eq(BlogUser::getIsDelete, false)
                .in(BlogUser::getRoleId, roleIdList);
        return this.list(queryWrapper);
    }

    @Override
    public List<BlogUser> getByResourceId(Long resourceId) {
        List<Long> roleIdList = blogRoleResourceService.queryRoleIdListByResourceId(resourceId);
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        if (CollectionUtils.isEmpty(roleIdList)) {
            return Collections.emptyList();
        }
        queryWrapper
                .eq(BlogUser::getIsDelete, false)
                .in(BlogUser::getRoleId, roleIdList);
        return this.list(queryWrapper);
    }
}
