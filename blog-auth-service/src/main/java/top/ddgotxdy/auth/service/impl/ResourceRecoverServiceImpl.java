package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogResourceService;
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
@AuthEventSelector(AuthEvent.RESOURCE_RECOVER)
@Service
@Slf4j
public class ResourceRecoverServiceImpl extends AbstractAuthService {
    @Resource
    private BlogResourceService blogResourceService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.RESOURCE_RECOVER_ERROR.getCode(), "用户id为空");
        }
        // 2. 资源 id 列表校验
        List<Long> resourceIds = authContext.getResourceIds();
        if (CollectionUtils.isEmpty(resourceIds)) {
            throw new BlogException(ResultCode.RESOURCE_RECOVER_ERROR.getCode(), "资源id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        List<Long> resourceIds = authContext.getResourceIds();
        boolean isOk = blogResourceService.recoverBatchByIds(resourceIds);
        if (isOk) {
            authContext.setResourceIds(Collections.emptyList());
        }
    }
}
