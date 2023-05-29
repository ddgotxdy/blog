package top.ddgotxdy.auth.service.impl;

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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private BlogUserService blogUserService;
    @Resource
    private LoginService loginService;
    @Resource
    private BlogRoleMenuService blogRoleMenuService;

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
            // 关系表删除
            boolean ok = blogRoleMenuService.deleteByMenuId(menuId);
            if (!ok) {
                menuIdsRemain.add(menuId);
            } else {
                // 菜单表删除
                ok = blogMenuService.deleteById(menuId);
                if (!ok) {
                    menuIdsRemain.add(menuId);
                }
            }
        });
        // 去掉没有被清空的
        menuIds.removeAll(menuIdsRemain);
        List<Long> roleIdList = blogRoleMenuService.queryRoleIdListByMenuIdList(menuIds);
        // 对应的用户
        List<Long> userIdList = blogUserService.queryByRoleIdList(roleIdList);
        // 更新用户redis基本信息
        loginService.refreshByBatchIds(userIdList);
        // 未删除的返回
        authContext.setMenuIds(menuIdsRemain);
    }
}
