package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.constant.RedisPrefix;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.util.RedisCache;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.USER_DELETE)
@Service
@Slf4j
public class UserDeleteServiceImpl extends AbstractAuthService {
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private RedisCache redisCache;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.USER_DELETE_ERROR.getCode(), "用户id为空");
        }
        // 2. user id 列表是否传过来
        List<Long> userIds = authContext.getUserIds();
        if (CollectionUtils.isEmpty(userIds)) {
            throw new BlogException(ResultCode.USER_DELETE_ERROR.getCode(), "user id列表为空");
        }
        // 3. 包含当前user id
        if (userIds.contains(userId)) {
            throw new BlogException(ResultCode.USER_DELETE_ERROR.getCode(), "不能删除当前用户");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        List<Long> userIdsRemain = new ArrayList<>();
        List<Long> userIds = authContext.getUserIds();
        userIds.forEach(userId -> {
            // 删除当前用户
            boolean ok = blogUserService.deleteById(userId);
            if (ok) {
                // 删除当前用户对应的登录redis信息
                String redisKey = RedisPrefix.LOGIN + userId;
                redisCache.deleteObject(redisKey);
            } else {
                userIdsRemain.add(userId);
            }
        });
        authContext.setUserIds(userIdsRemain);
    }
}
