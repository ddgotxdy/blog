package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogRoleMenuService;
import top.ddgotxdy.auth.service.BlogRoleService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.ROLE_DELETE)
@Service
@Slf4j
public class RoleDeleteServiceImpl extends AbstractAuthService {
    @Resource
    private BlogRoleService blogRoleService;
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private BlogRoleMenuService blogRoleMenuService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.ROLE_DELETE_ERROR.getCode(), "用户id为空");
        }
        // 2. 角色 id 校验
        List<Long> roleIds = authContext.getRoleIds();
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new BlogException(ResultCode.ROLE_DELETE_ERROR.getCode(), "角色id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        List<Long> roleIds = authContext.getRoleIds();
        List<Long> roleIdsRemain = new ArrayList<>();
        roleIds.forEach(roleId -> {
            // 判断当前用户是否被用户使用
            List<BlogUser> blogUserList = blogUserService.getByRoleIdAll(roleId);
            boolean anyMatch = blogUserList.stream()
                    .anyMatch(blogUser -> !blogUser.getIsDelete());
            if (anyMatch) {
                roleIdsRemain.add(roleId);
            } else {
                // 删除role表
                blogRoleService.deleteById(roleId);
                // 删除role_menu表
                blogRoleMenuService.deleteByRoleId(roleId);
                blogUserList.forEach(blogUser -> {
                    // 删除当前用户对此角色的依赖
                    BlogUser blogUserUpdate = new BlogUser();
                    blogUserUpdate.setRoleId(0L);
                    blogUserUpdate.setRoleId(blogUser.getRoleId());
                    blogUserService.updateById(blogUserUpdate);
                });
            }
        });
        authContext.setRoleIds(roleIdsRemain);
    }
}
