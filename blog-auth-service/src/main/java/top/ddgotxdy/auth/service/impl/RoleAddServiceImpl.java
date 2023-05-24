package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.convert.Context2EntityConvert;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogRoleService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogRole;

import javax.annotation.Resource;

import static top.ddgotxdy.auth.constant.ValidateConstant.*;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.ROLE_ADD)
@Service
@Slf4j
public class RoleAddServiceImpl extends AbstractAuthService {
    @Resource
    private BlogRoleService blogRoleService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. 角色名称
        String roleName = authContext.getRoleName();
        if (StringUtils.length(roleName) < ROLE_NAME_MIN_LENGTH
                || StringUtils.length(roleName) > ROLE_NAME_MAX_LENGTH) {
            throw new BlogException(ResultCode.ROLE_ADD_ERROR.getCode(), "角色名长度错误");
        }
        // 2. 描述长度
        String roleDesc = authContext.getRoleDesc();
        if (StringUtils.length(roleDesc) > DESC_MAX_LENGTH) {
            throw new BlogException(ResultCode.ROLE_ADD_ERROR.getCode(), "描述长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogRole blogRole = Context2EntityConvert.authContext2RoleForAdd(authContext);
        blogRoleService.save(blogRole);
        authContext.setRoleId(blogRole.getRoleId());
    }
}
