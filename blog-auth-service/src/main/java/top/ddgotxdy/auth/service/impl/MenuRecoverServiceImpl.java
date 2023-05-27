package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogMenuService;
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
@AuthEventSelector(AuthEvent.MENU_RECOVER)
@Service
@Slf4j
public class MenuRecoverServiceImpl extends AbstractAuthService {
    @Resource
    private BlogMenuService blogMenuService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.MENU_RECOVER_ERROR.getCode(), "用户id为空");
        }
        // 2. 菜单 id 校验
        List<Long> menuIds = authContext.getMenuIds();
        if (CollectionUtils.isEmpty(menuIds)) {
            throw new BlogException(ResultCode.MENU_RECOVER_ERROR.getCode(), "菜单id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        List<Long> menuIds = authContext.getMenuIds();
        boolean isOk = blogMenuService.recoverBatchByIds(menuIds);
        if (isOk) {
            authContext.setMenuIds(Collections.emptyList());
        }
    }
}
