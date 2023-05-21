package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.UserEmailUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserInfoUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserPasswordUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserRoleUpdateApiParam;
import top.ddgotxdy.api.model.view.UserInfoPageListView;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogAuthBizService {
    /**
     * 注册
     * @param userAddApiParam
     * @return
     */
    IdView register(UserAddApiParam userAddApiParam);

    /**
     * 登录
     * @param userLoginApiModel
     * @return
     */
    String login(UserLoginApiModel userLoginApiModel);

    /**
     * 登出
     */
    void logout();

    /**
     * 获取用户基本信息
     * @return UserInfoView
     */
    UserInfoView getUserInfo();

    /**
     * 更新密码
     * @param userPasswordUpdateApiParam 更新密码参数
     * @return IdView
     */
    IdView updatePassword(UserPasswordUpdateApiParam userPasswordUpdateApiParam);

    /**
     * 更新邮箱
     * @param userEmailUpdateApiParam 更新邮箱参数
     * @return IdView
     */
    IdView updateEmail(UserEmailUpdateApiParam userEmailUpdateApiParam);

    /**
     * 更新用户信息
     * @param userInfoUpdateApiParam 更新用户信息参数
     * @return IdView
     */
    IdView updateUserInfo(UserInfoUpdateApiParam userInfoUpdateApiParam);

    /**
     * 更新角色
     * @param userRoleUpdateApiParam 更新角色参数
     * @return IdView
     */
    IdView updateRole(UserRoleUpdateApiParam userRoleUpdateApiParam);

    /**
     * 分页获取用户登录列表
     * @param userInfoQueryApiParamPageQry 分页查询参数
     * @return UserInfoPageListView
     */
    PageResult<UserInfoPageListView> getUserInfoList(PageQry<UserInfoQueryApiParam> userInfoQueryApiParamPageQry);
}
