package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.convert.Context2EntityConvert;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.USER_ROLE_UPDATE)
@Service
@Slf4j
public class UserRoleUpdateServiceImpl extends AbstractAuthService {
    @Resource
    private BlogUserService blogUserService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.USER_ROLE_UPDATE_ERROR.getCode(), "用户id为空");
        }
        // 2. 角色 id 校验
        Long roleId = authContext.getRoleId();
        if (Objects.isNull(roleId)) {
            throw new BlogException(ResultCode.USER_ROLE_UPDATE_ERROR.getCode(), "角色id为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogUser blogUser = Context2EntityConvert.authContext2UserForUpdate(authContext);
        blogUserService.updateById(blogUser);
        // 更新redis里面的信息
        updateUserInfoFromRedis(authContext);
    }
}
