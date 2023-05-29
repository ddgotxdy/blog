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
import top.ddgotxdy.auth.service.BlogResourceService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogResource;

import javax.annotation.Resource;
import java.util.Objects;

import static top.ddgotxdy.auth.constant.ValidateConstant.*;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.RESOURCE_ADD)
@Service
@Slf4j
public class ResourceAddServiceImpl extends AbstractAuthService {
    @Resource
    private BlogResourceService blogResourceService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.RESOURCE_ADD_ERROR.getCode(), "用户id为空");
        }
        // 2. 资源名校验
        String resourceName = authContext.getResourceName();
        if (StringUtils.length(resourceName) < RESOURCE_NAME_MIN_LENGTH
                || StringUtils.length(resourceName) > RESOURCE_NAME_MAX_LENGTH) {
            throw new BlogException(ResultCode.RESOURCE_ADD_ERROR.getCode(), "资源名长度错误");
        }
        // 3. uri校验
        String uri = authContext.getUri();
        if (StringUtils.length(uri) < URI_NAME_MIN_LENGTH
                || StringUtils.length(uri) > URI_NAME_MAX_LENGTH) {
            throw new BlogException(ResultCode.RESOURCE_ADD_ERROR.getCode(), "uri长度错误");
        }
        // 4. 资源描述【可选】
        String resourceDesc = authContext.getResourceDesc();
        if (StringUtils.length(resourceDesc) > DESC_MAX_LENGTH) {
            throw new BlogException(ResultCode.RESOURCE_ADD_ERROR.getCode(), "资源描述长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogResource blogResource = Context2EntityConvert.authContext2resourceForAdd(authContext);
        blogResourceService.save(blogResource);
        authContext.setResourceId(blogResource.getResourceId());
    }
}
