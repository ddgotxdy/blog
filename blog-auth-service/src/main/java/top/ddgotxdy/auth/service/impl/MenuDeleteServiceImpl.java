package top.ddgotxdy.auth.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.*;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.MENU_DELETE)
@Service
@Slf4j
public class MenuDeleteServiceImpl extends AbstractAuthService {
    @Resource
    private BlogMenuService blogMenuService;
    @Resource
    private BlogRoleService blogRoleService;
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private LoginService loginService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.MENU_DELETE_ERROR.getCode(), "用户id为空");
        }
        // 2. 菜单 id 校验
        List<Long> menuIds = authContext.getMenuIds();
        if (CollectionUtils.isEmpty(menuIds)) {
            throw new BlogException(ResultCode.MENU_DELETE_ERROR.getCode(), "菜单id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        List<Long> menuIds = authContext.getMenuIds();
        List<Long> menuIdsRemain = new ArrayList<>();
        // 删除角色中对当前菜单的引用
        menuIds.forEach(menuId -> {
            List<BlogRole> blogRoleList = blogRoleService.getByMenuId(menuId);
            blogRoleList.forEach(blogRole -> {
                List<Long> menuIdList = JSON.parseArray(blogRole.getMenuIds(), Long.class);
                menuIdList.remove(menuId);
                BlogRole blogRoleUpdate = new BlogRole();
                blogRoleUpdate.setMenuIds(JSON.toJSONString(menuIdList));
                blogRoleUpdate.setRoleId(blogRole.getRoleId());
                boolean ok = blogRoleService.updateById(blogRoleUpdate);
                if (!ok) {
                    menuIdsRemain.add(menuId);
                }
            });
            if (!menuIdsRemain.contains(menuId)) {
                boolean ok = blogMenuService.deleteById(menuId);
                if (!ok) {
                    menuIdsRemain.add(menuId);
                }
            }
        });
        // 去重
        List<Long> menuIdsRemainDistinct = menuIdsRemain.stream()
                .distinct()
                .collect(Collectors.toList());
        // 同时，对删除了的菜单，需要重新加载redis
        List<Long> userIds = new ArrayList<>();
        menuIds.forEach(menuId -> {
            if (!menuIdsRemainDistinct.contains(menuId)) {
                List<BlogUser> blogUserList = blogUserService.getByMenuId(menuId);
                List<Long> userIdList = blogUserList.stream()
                        .map(BlogUser::getUserId)
                        .collect(Collectors.toList());
                userIds.addAll(userIdList);
            }
        });
        List<Long> userIdsDistinct = userIds.stream()
                .distinct()
                .collect(Collectors.toList());
        loginService.refreshByBatchIds(userIdsDistinct);
        // 未删除的返回
        authContext.setMenuIds(menuIdsRemainDistinct);
    }
}
