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
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.auth.service.LoginService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogResource;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static top.ddgotxdy.auth.constant.ValidateConstant.*;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.RESOURCE_UPDATE)
@Service
@Slf4j
public class ResourceUpdateServiceImpl extends AbstractAuthService {
    @Resource
    private BlogResourceService blogResourceService;
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private LoginService loginService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.RESOURCE_UPDATE_ERROR.getCode(), "用户id为空");
        }
        // 2. resource id 校验
        Long resourceId = authContext.getResourceId();
        if (Objects.isNull(resourceId)) {
            throw new BlogException(ResultCode.RESOURCE_UPDATE_ERROR.getCode(), "菜单id为空");
        }
        // 3. 资源名校验
        String resourceName = authContext.getResourceName();
        if (StringUtils.isNotEmpty(resourceName)
                && (StringUtils.length(resourceName) < RESOURCE_NAME_MIN_LENGTH
                || StringUtils.length(resourceName) > RESOURCE_NAME_MAX_LENGTH)) {
            throw new BlogException(ResultCode.RESOURCE_UPDATE_ERROR.getCode(), "资源名长度错误");
        }
        // 4. uri校验
        String uri = authContext.getUri();
        if (StringUtils.isNotEmpty(uri)
                && (StringUtils.length(uri) < URI_NAME_MIN_LENGTH
                || StringUtils.length(uri) > URI_NAME_MAX_LENGTH)) {
            throw new BlogException(ResultCode.RESOURCE_UPDATE_ERROR.getCode(), "uri长度错误");
        }
        // 5. 资源描述【可选】
        String resourceDesc = authContext.getResourceDesc();
        if (StringUtils.length(resourceDesc) > DESC_MAX_LENGTH) {
            throw new BlogException(ResultCode.RESOURCE_UPDATE_ERROR.getCode(), "资源描述长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogResource blogResource = Context2EntityConvert.authContext2ResourceForUpdate(authContext);
        blogResourceService.updateById(blogResource);
        if (Objects.nonNull(authContext.getUri())) {
            // 更新缓存
            List<BlogUser> blogUserList = blogUserService.getByResourceId(authContext.getResourceId());
            List<Long> userIds = blogUserList.stream()
                    .map(BlogUser::getUserId)
                    .collect(Collectors.toList());
            loginService.refreshByBatchIds(userIds);
        }
    }
}
