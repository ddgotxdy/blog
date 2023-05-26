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
import top.ddgotxdy.auth.service.BlogMenuService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogMenu;

import javax.annotation.Resource;
import java.util.Objects;

import static top.ddgotxdy.auth.constant.ValidateConstant.*;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.MENU_ADD)
@Service
@Slf4j
public class MenuAddServiceImpl extends AbstractAuthService {
    @Resource
    private BlogMenuService blogMenuService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.MENU_ADD_ERROR.getCode(), "用户id为空");
        }
        // 2. 菜单名校验
        String menuName = authContext.getMenuName();
        if (StringUtils.length(menuName) < MENU_NAME_MIN_LENGTH
                || StringUtils.length(menuName) > MENU_NAME_MAX_LENGTH) {
            throw new BlogException(ResultCode.MENU_ADD_ERROR.getCode(), "菜单名长度错误");
        }
        // 3. 路由(uri)地址校验【可选】
        String path = authContext.getPath();
        if (StringUtils.isNotEmpty(path)
                && (StringUtils.length(path) < PATH_NAME_MIN_LENGTH
                || StringUtils.length(path) > PATH_NAME_MAX_LENGTH)) {
            throw new BlogException(ResultCode.MENU_ADD_ERROR.getCode(), "路由长度错误");
        }
        // 4. 组件地址校验【可选】
        String component = authContext.getComponent();
        if (StringUtils.isNotEmpty(component)
                && (StringUtils.length(component) < COMPONENT_NAME_MIN_LENGTH
                || StringUtils.length(component) > COMPONENT_NAME_MAX_LENGTH)) {
            throw new BlogException(ResultCode.MENU_ADD_ERROR.getCode(), "组件长度错误");
        }
        // 5. 权限标识
        String perms = authContext.getPerms();
        if (StringUtils.length(perms) < PERMS_NAME_MIN_LENGTH
                || StringUtils.length(perms) > PERMS_NAME_MAX_LENGTH) {
            throw new BlogException(ResultCode.MENU_ADD_ERROR.getCode(), "权限标识长度错误");
        }
        // 6. 权限描述【可选】
        String menuDesc = authContext.getMenuDesc();
        if (StringUtils.length(menuDesc) > DESC_MAX_LENGTH) {
            throw new BlogException(ResultCode.MENU_ADD_ERROR.getCode(), "权限描述长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogMenu blogMenu = Context2EntityConvert.authContext2MenuForAdd(authContext);
        blogMenuService.save(blogMenu);
        authContext.setRoleId(blogMenu.getMenuId());
    }
}
