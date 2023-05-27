package top.ddgotxdy.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.service.BlogMenuService;
import top.ddgotxdy.auth.service.BlogRoleService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.dal.entity.BlogMenu;
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
                .eq(BlogUser::getRoleId, roleId);
        return this.list(queryWrapper);
    }

    @Override
    public LoginUser loadUserByUserId(Long userId) {
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
        // 获取角色表
        Long roleId = blogUser.getRoleId();
        BlogRole blogRole = blogRoleService.getById(roleId);
        // 如果没有查询到角色，就返回默认角色权限 TODO
        if(Objects.isNull(blogRole) || blogRole.getIsDelete()) {
            List<String> permissionDefault = Collections.emptyList();
            return new LoginUser(blogUser, permissionDefault, permissionDefault);
        }
        // 查询对应的权限信息
        String menuIds = blogRole.getMenuIds();
        List<Long> menuIdList = JSON.parseArray(menuIds, Long.class);
        List<BlogMenu> blogMenus = blogMenuService.listByIds(menuIdList);
        // 去重且过滤删除了的
        List<String> permissions = blogMenus.stream()
                .filter(blogMenu -> !blogMenu.getIsDelete())
                .map(BlogMenu::getPerms)
                .distinct()
                .collect(Collectors.toList());
        // 路由去重
        List<String> paths = blogMenus.stream()
                .filter(blogMenu -> !blogMenu.getIsDelete())
                .map(BlogMenu::getPath)
                .distinct()
                .collect(Collectors.toList());
        // 封装成UserDetails
        return new LoginUser(blogUser, permissions, paths);
    }

    @Override
    public LoginUser loadUserByUsername(String username) {
        // 查询用户信息
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        // 可以根据用户名或者邮箱进行登录
        queryWrapper
                .eq(BlogUser::getIsDelete, false)
                .and(qw ->
                        qw.eq(BlogUser::getUsername, username)
                                .or()
                                .eq(BlogUser::getEmail, username));
        List<BlogUser> blogUserList = this.list(queryWrapper);
        // 如果没有查询到用户，就抛出异常
        if(CollectionUtils.isEmpty(blogUserList)) {
            throw new BlogException(ResultCode.LOGIN_ERROR);
        }
        BlogUser blogUser = blogUserList.get(0);
        // 获取角色表
        Long roleId = blogUser.getRoleId();
        BlogRole blogRole = blogRoleService.getById(roleId);
        // 如果没有查询到角色，就返回默认角色权限 TODO
        if(Objects.isNull(blogRole) || blogRole.getIsDelete()) {
            List<String> permissionDefault = Collections.emptyList();
            return new LoginUser(blogUser, permissionDefault, permissionDefault);
        }
        // 查询对应的权限信息
        String menuIds = blogRole.getMenuIds();
        List<Long> menuIdList = JSON.parseArray(menuIds, Long.class);
        List<BlogMenu> blogMenus = blogMenuService.listByIds(menuIdList);
        // 去重且过滤删除了的
        List<String> permissions = blogMenus.stream()
                .filter(blogMenu -> !blogMenu.getIsDelete())
                .map(BlogMenu::getPerms)
                .distinct()
                .collect(Collectors.toList());
        // 路由去重
        List<String> paths = blogMenus.stream()
                .filter(blogMenu -> !blogMenu.getIsDelete())
                .map(BlogMenu::getPath)
                .distinct()
                .collect(Collectors.toList());
        // 封装成UserDetails
        return new LoginUser(blogUser, permissions, paths);
    }

    @Override
    public List<BlogUser> getByMenuId(Long menuId) {
        List<BlogRole> blogRoleList = blogRoleService.getByMenuId(menuId);
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogUser::getIsDelete, false)
                .in(BlogUser::getRoleId, blogRoleList);
        return this.list(queryWrapper);
    }
}
