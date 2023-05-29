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
@AuthEventSelector(AuthEvent.RESOURCE_DELETE)
@Service
@Slf4j
public class ResourceDeleteServiceImpl extends AbstractAuthService {
    @Resource
    private BlogResourceService blogResourceService;
    @Resource
    private BlogRoleResourceService blogRoleResourceService;
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private LoginService loginService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.RESOURCE_DELETE_ERROR.getCode(), "用户id为空");
        }
        // 2. 资源 id 列表校验
        List<Long> resourceIds = authContext.getResourceIds();
        if (CollectionUtils.isEmpty(resourceIds)) {
            throw new BlogException(ResultCode.RESOURCE_DELETE_ERROR.getCode(), "资源id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        List<Long> resourceIds = authContext.getResourceIds();
        List<Long> resourceIdsRemain = new ArrayList<>();
        // 删除角色中对当前菜单的引用
        resourceIds.forEach(resourceId -> {
            // 关系表删除
            blogRoleResourceService.deleteByResourceId(resourceId);
            boolean ok = blogResourceService.deleteById(resourceId);
            if (!ok) {
                resourceIdsRemain.add(resourceId);
            }
        });
        // 去掉没有被清空的
        resourceIds.removeAll(resourceIdsRemain);
        List<Long> roleIdList = blogRoleResourceService.queryRoleIdListByResourceIdList(resourceIds);
        // 对应的用户
        List<Long> userIdList = blogUserService.queryByRoleIdList(roleIdList);
        // 更新用户redis基本信息
        loginService.refreshByBatchIds(userIdList);
        // 未删除的返回
        authContext.setResourceIds(resourceIdsRemain);
    }
}
