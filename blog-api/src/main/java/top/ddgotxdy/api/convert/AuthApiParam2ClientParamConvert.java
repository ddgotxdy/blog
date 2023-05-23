package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.UserEmailUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserInfoUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserPasswordUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserRoleUpdateApiParam;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;
import top.ddgotxdy.common.model.auth.recoverparam.UserDeleteParam;
import top.ddgotxdy.common.model.auth.updateparam.UserEmailUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserInfoUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserPasswordUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserRoleUpdateParam;
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
}
