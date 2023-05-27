package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.MenuAddApiParam;
import top.ddgotxdy.api.model.addparam.RoleAddApiParam;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.MenuQueryApiParam;
import top.ddgotxdy.api.model.queryparam.RoleQueryApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.*;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.auth.addparam.MenuAddParam;
import top.ddgotxdy.common.model.auth.addparam.RoleAddParam;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.MenuDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.RoleDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.queryparam.MenuQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.RoleQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;
import top.ddgotxdy.common.model.auth.recoverparam.MenuRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.RoleRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.UserDeleteParam;
import top.ddgotxdy.common.model.auth.updateparam.*;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
public class AuthApiParam2ClientParamConvert {
    private AuthApiParam2ClientParamConvert() { }
    public static UserAddParam addApiParam2AddParam(UserAddApiParam userAddApiParam) {
        UserAddParam userAddParam = new UserAddParam();
        BeanCopyUtil.copyProperties(userAddApiParam, userAddParam);
        return userAddParam;
    }

    public static UserLoginModel apiParam2Param(UserLoginApiModel userLoginApiModel) {
        UserLoginModel userLoginModel = new UserLoginModel();
        BeanCopyUtil.copyProperties(userLoginApiModel, userLoginModel);
        return userLoginModel;
    }

    public static UserPasswordUpdateParam apiParam2Param(UserPasswordUpdateApiParam userPasswordUpdateApiParam) {
        UserPasswordUpdateParam userPasswordUpdateParam = new UserPasswordUpdateParam();
        BeanCopyUtil.copyProperties(userPasswordUpdateApiParam, userPasswordUpdateParam);
        Long userId = ContextScope.getUserId();
        userPasswordUpdateParam.setUserId(userId);
        return userPasswordUpdateParam;
    }

    public static UserEmailUpdateParam apiParam2Param(UserEmailUpdateApiParam userEmailUpdateApiParam) {
        UserEmailUpdateParam userEmailUpdateParam = new UserEmailUpdateParam();
        BeanCopyUtil.copyProperties(userEmailUpdateApiParam, userEmailUpdateParam);
        Long userId = ContextScope.getUserId();
        userEmailUpdateParam.setUserId(userId);
        return userEmailUpdateParam;
    }

    public static UserInfoUpdateParam apiParam2Param(UserInfoUpdateApiParam userInfoUpdateApiParam) {
        UserInfoUpdateParam userInfoUpdateParam = new UserInfoUpdateParam();
        BeanCopyUtil.copyProperties(userInfoUpdateApiParam, userInfoUpdateParam);
        if (Objects.nonNull(userInfoUpdateApiParam.getSex())) {
            userInfoUpdateParam.setSex(userInfoUpdateApiParam.getSex().getCode());
        }
        Long userId = ContextScope.getUserId();
        userInfoUpdateParam.setUserId(userId);
        return userInfoUpdateParam;
    }

    public static UserRoleUpdateParam apiParam2Param(UserRoleUpdateApiParam userRoleUpdateApiParam) {
        UserRoleUpdateParam userRoleUpdateParam = new UserRoleUpdateParam();
        BeanCopyUtil.copyProperties(userRoleUpdateApiParam, userRoleUpdateParam);
        return userRoleUpdateParam;
    }

    public static PageQry<UserInfoQueryParam> queryApiParam2QueryParam(PageQry<UserInfoQueryApiParam> userInfoQueryApiParamPageQry) {
        UserInfoQueryParam userInfoQueryParam = new UserInfoQueryParam();
        // 范型复制
        UserInfoQueryApiParam userInfoQueryApiParam = userInfoQueryApiParamPageQry.getQueryParam();
        BeanCopyUtil.copyProperties(userInfoQueryApiParam, userInfoQueryParam);
        if (Objects.nonNull(userInfoQueryApiParam.getSex())) {
            userInfoQueryParam.setSex(userInfoQueryApiParam.getSex().getCode());
        }
        // 分页参数复制
        PageQry<UserInfoQueryParam> userInfoQueryParamPageQry = new PageQry<>();
        BeanCopyUtil.copyProperties(userInfoQueryApiParamPageQry, userInfoQueryParamPageQry);
        userInfoQueryParamPageQry.setQueryParam(userInfoQueryParam);
        return userInfoQueryParamPageQry;
    }

    public static UserDeleteParam deleteApiParam2DeleteParam(List<Long> userIdList) {
        UserDeleteParam userDeleteParam = new UserDeleteParam();
        userDeleteParam.setUserIds(userIdList);
        Long userId = ContextScope.getUserId();
        userDeleteParam.setUserId(userId);
        return userDeleteParam;
    }

    public static UserRecoverParam recoverApiParam2RecoverParam(List<Long> userIdList) {
        UserRecoverParam userRecoverParam = new UserRecoverParam();
        userRecoverParam.setUserIds(userIdList);
        Long userId = ContextScope.getUserId();
        userRecoverParam.setUserId(userId);
        return userRecoverParam;
    }

    public static RoleAddParam addApiParam2AddParam(RoleAddApiParam roleAddApiParam) {
        RoleAddParam roleAddParam = new RoleAddParam();
        BeanCopyUtil.copyProperties(roleAddApiParam, roleAddParam);
        Long userId = ContextScope.getUserId();
        roleAddParam.setUserId(userId);
        return roleAddParam;
    }

    public static RoleUpdateParam apiParam2Param(RoleUpdateApiParam roleUpdateApiParam) {
        RoleUpdateParam roleUpdateParam = new RoleUpdateParam();
        BeanCopyUtil.copyProperties(roleUpdateApiParam, roleUpdateParam);
        Long userId = ContextScope.getUserId();
        roleUpdateParam.setUserId(userId);
        return roleUpdateParam;
    }

    public static RoleDeleteParam deleteApiParam2RoleDeleteParam(List<Long> roleIdList) {
        RoleDeleteParam roleDeleteParam = new RoleDeleteParam();
        roleDeleteParam.setRoleIds(roleIdList);
        Long userId = ContextScope.getUserId();
        roleDeleteParam.setUserId(userId);
        return roleDeleteParam;
    }

    public static RoleRecoverParam recoverApiParam2RoleRecoverParam(List<Long> roleIdList) {
        RoleRecoverParam roleRecoverParam = new RoleRecoverParam();
        roleRecoverParam.setRoleIds(roleIdList);
        Long userId = ContextScope.getUserId();
        roleRecoverParam.setUserId(userId);
        return roleRecoverParam;
    }

    public static PageQry<RoleQueryParam> queryApiParam2RoleQueryParam(PageQry<RoleQueryApiParam> roleQueryApiParamPageQry) {
        RoleQueryParam roleQueryParam = new RoleQueryParam();
        // 范型复制
        RoleQueryApiParam roleQueryApiParam = roleQueryApiParamPageQry.getQueryParam();
        BeanCopyUtil.copyProperties(roleQueryApiParam, roleQueryParam);
        // 分页参数复制
        PageQry<RoleQueryParam> roleQueryParamPageQry = new PageQry<>();
        BeanCopyUtil.copyProperties(roleQueryApiParamPageQry, roleQueryParamPageQry);
        roleQueryParamPageQry.setQueryParam(roleQueryParam);
        return roleQueryParamPageQry;
    }

    public static MenuAddParam addApiParam2AddParam(MenuAddApiParam menuAddApiParam) {
        MenuAddParam menuAddParam = new MenuAddParam();
        BeanCopyUtil.copyProperties(menuAddApiParam, menuAddParam);
        Long userId = ContextScope.getUserId();
        menuAddParam.setUserId(userId);
        return menuAddParam;
    }

    public static PageQry<MenuQueryParam> queryApiParam2MenuQueryParam(
            PageQry<MenuQueryApiParam> menuQueryApiParamPageQry
    ) {
        MenuQueryParam menuQueryParam = new MenuQueryParam();
        // 范型复制
        MenuQueryApiParam menuQueryApiParam = menuQueryApiParamPageQry.getQueryParam();
        BeanCopyUtil.copyProperties(menuQueryApiParam, menuQueryParam);
        // 分页参数复制
        PageQry<MenuQueryParam> menuQueryParamPageQry = new PageQry<>();
        BeanCopyUtil.copyProperties(menuQueryApiParamPageQry, menuQueryParamPageQry);
        menuQueryParamPageQry.setQueryParam(menuQueryParam);
        return menuQueryParamPageQry;
    }

    public static MenuUpdateParam apiParam2Param(MenuUpdateApiParam menuUpdateApiParam) {
        MenuUpdateParam menuUpdateParam = new MenuUpdateParam();
        BeanCopyUtil.copyProperties(menuUpdateApiParam, menuUpdateParam);
        Long userId = ContextScope.getUserId();
        menuUpdateParam.setUserId(userId);
        return menuUpdateParam;
    }

    public static MenuDeleteParam deleteApiParam2MenuDeleteParam(List<Long> menuIdList) {
        MenuDeleteParam menuDeleteParam = new MenuDeleteParam();
        menuDeleteParam.setMenuIds(menuIdList);
        Long userId = ContextScope.getUserId();
        menuDeleteParam.setUserId(userId);
        return menuDeleteParam;
    }

    public static MenuRecoverParam recoverApiParam2MenuRecoverParam(List<Long> menuIdList) {
        MenuRecoverParam menuRecoverParam = new MenuRecoverParam();
        menuRecoverParam.setMenuIds(menuIdList);
        Long userId = ContextScope.getUserId();
        menuRecoverParam.setUserId(userId);
        return menuRecoverParam;
    }
}
