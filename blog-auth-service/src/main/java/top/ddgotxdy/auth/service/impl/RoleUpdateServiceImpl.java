package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.convert.Context2EntityConvert;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogRoleService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.constant.RedisPrefix;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.common.util.JwtUtil;
import top.ddgotxdy.common.util.RedisCache;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static top.ddgotxdy.auth.constant.ValidateConstant.*;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.ROLE_UPDATE)
@Service
@Slf4j
public class RoleUpdateServiceImpl extends AbstractAuthService {
    @Resource
    private BlogRoleService blogRoleService;
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private RedisCache redisCache;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.ROLE_UPDATE_ERROR.getCode(), "用户id为空");
        }
        // 2. 角色 id 校验
        Long roleId = authContext.getRoleId();
        if (Objects.isNull(roleId)) {
            throw new BlogException(ResultCode.ROLE_UPDATE_ERROR.getCode(), "角色id为空");
        }
        // 3. 角色名称
        String roleName = authContext.getRoleName();
        if (StringUtils.isNotEmpty(roleName)
                && (StringUtils.length(roleName) < ROLE_NAME_MIN_LENGTH
                || StringUtils.length(roleName) > ROLE_NAME_MAX_LENGTH)) {
            throw new BlogException(ResultCode.ROLE_ADD_ERROR.getCode(), "角色名长度错误");
        }
        // 4. 描述长度
        String roleDesc = authContext.getRoleDesc();
        if (StringUtils.isNotEmpty(roleDesc)
                && StringUtils.length(roleDesc) > DESC_MAX_LENGTH) {
            throw new BlogException(ResultCode.ROLE_ADD_ERROR.getCode(), "描述长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogRole blogRole = Context2EntityConvert.authContext2RoleForUpdate(authContext);
        blogRoleService.updateById(blogRole);
        // 同步更新用户的redis信息
        if (Objects.nonNull(authContext.getMenuIds())) {
            List<BlogUser> blogUserList = blogUserService.getByRoleId(authContext.getRoleId());
            blogUserList.forEach(blogUser -> {
                LoginUser loginUser = (LoginUser) userDetailsService.loadUserByUsername(blogUser.getUsername());
                String userId = loginUser.getUser().getUserId().toString();
                String jwt = JwtUtil.createJWT(userId);
                // 把完整的用户信息存入redis  userid作为key
                redisCache.setCacheObject(RedisPrefix.LOGIN + userId, loginUser);
            });
        }
    }
}
