package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogRoleService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.ROLE_RECOVER)
@Service
@Slf4j
public class RoleRecoverServiceImpl extends AbstractAuthService {
    @Resource
    private BlogRoleService blogRoleService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.ROLE_RECOVER_ERROR.getCode(), "用户id为空");
        }
        // 2. 角色 id 校验
        List<Long> roleIds = authContext.getRoleIds();
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new BlogException(ResultCode.ROLE_RECOVER_ERROR.getCode(), "角色id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        List<Long> roleIds = authContext.getRoleIds();
        boolean isOk = blogRoleService.recoverBatchByIds(roleIds);
        if (isOk) {
            authContext.setRoleIds(Collections.emptyList());
        }
    }
}
