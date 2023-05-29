package top.ddgotxdy.auth.convert;

import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.common.model.auth.addparam.MenuAddParam;
import top.ddgotxdy.common.model.auth.addparam.ResourceAddParam;
import top.ddgotxdy.common.model.auth.addparam.RoleAddParam;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.MenuDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.ResourceDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.RoleDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.MenuRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.ResourceRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.RoleRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.UserDeleteParam;
import top.ddgotxdy.common.model.auth.updateparam.*;
import top.ddgotxdy.common.util.BeanCopyUtil;

/**
 * @author: ddgo
 * @description:
 */
public class Param2ContextConvert {
    private Param2ContextConvert() { }
    public static AuthContext addParamConvert(UserAddParam userAddParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userAddParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_REGISTER);
        return authContext;
    }

    public static AuthContext updateParamConvert(UserInfoUpdateParam userInfoUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userInfoUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_INFO_UPDATE);
        return authContext;
    }

    public static AuthContext updateParamConvert(UserPasswordUpdateParam userPasswordUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userPasswordUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_PASSWORD_UPDATE);
        return authContext;
    }

    public static AuthContext updateParamConvert(UserEmailUpdateParam userEmailUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userEmailUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_EMAIL_UPDATE);
        return authContext;
    }

    public static AuthContext updateParamConvert(UserRoleUpdateParam userRoleUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userRoleUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_ROLE_UPDATE);
        return authContext;
    }

    public static AuthContext deleteParamConvert(UserDeleteParam userDeleteParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userDeleteParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_DELETE);
        return authContext;
    }

    public static AuthContext recoverParamConvert(UserRecoverParam userRecoverParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userRecoverParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_RECOVER);
        return authContext;
    }

    public static AuthContext addParamConvert(RoleAddParam roleAddParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(roleAddParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.ROLE_ADD);
        return authContext;
    }

    public static AuthContext updateParamConvert(RoleUpdateParam roleUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(roleUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.ROLE_UPDATE);
        return authContext;
    }

    public static AuthContext deleteParamConvert(RoleDeleteParam roleDeleteParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(roleDeleteParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.ROLE_DELETE);
        return authContext;
    }

    public static AuthContext recoverParamConvert(RoleRecoverParam roleRecoverParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(roleRecoverParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.ROLE_RECOVER);
        return authContext;
    }

    public static AuthContext addParamConvert(MenuAddParam menuAddParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(menuAddParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.MENU_ADD);
        return authContext;
    }

    public static AuthContext updateParamConvert(MenuUpdateParam menuUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(menuUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.MENU_UPDATE);
        return authContext;
    }

    public static AuthContext deleteParamConvert(MenuDeleteParam menuDeleteParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(menuDeleteParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.MENU_DELETE);
        return authContext;
    }

    public static AuthContext recoverParamConvert(MenuRecoverParam menuRecoverParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(menuRecoverParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.MENU_RECOVER);
        return authContext;
    }

    public static AuthContext addParamConvert(ResourceAddParam resourceAddParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(resourceAddParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.RESOURCE_ADD);
        return authContext;
    }

    public static AuthContext updateParamConvert(ResourceUpdateParam resourceUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(resourceUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.RESOURCE_UPDATE);
        return authContext;
    }

    public static AuthContext deleteParamConvert(ResourceDeleteParam resourceDeleteParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(resourceDeleteParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.RESOURCE_DELETE);
        return authContext;
    }

    public static AuthContext recoverParamConvert(ResourceRecoverParam resourceRecoverParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(resourceRecoverParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.RESOURCE_RECOVER);
        return authContext;
    }
}
