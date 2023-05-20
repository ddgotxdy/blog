package top.ddgotxdy.auth.convert;

import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.updateparam.UserEmailUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserInfoUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserPasswordUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserRoleUpdateParam;
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

    public static AuthContext addParamConvert(UserInfoUpdateParam userInfoUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userInfoUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_INFO_UPDATE);
        return authContext;
    }

    public static AuthContext addParamConvert(UserPasswordUpdateParam userPasswordUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userPasswordUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_PASSWORD_UPDATE);
        return authContext;
    }

    public static AuthContext addParamConvert(UserEmailUpdateParam userEmailUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userEmailUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_EMAIL_UPDATE);
        return authContext;
    }

    public static AuthContext addParamConvert(UserRoleUpdateParam userRoleUpdateParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userRoleUpdateParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.USER_ROLE_UPDATE);
        return authContext;
    }
}
