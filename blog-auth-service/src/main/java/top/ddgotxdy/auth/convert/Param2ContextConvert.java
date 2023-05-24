package top.ddgotxdy.auth.convert;

import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.common.model.auth.addparam.RoleAddParam;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.RoleDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
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
}
