package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.RoleAddApiParam;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.RoleQueryApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.*;
import top.ddgotxdy.api.model.view.RolePageListView;
import top.ddgotxdy.api.model.view.UserInfoPageListView;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.IdsView;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;

import java.util.List;

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
    IdView updateUserRole(UserRoleUpdateApiParam userRoleUpdateApiParam);

    /**
     * 分页获取用户登录列表
     * @param userInfoQueryApiParamPageQry 分页查询参数
     * @return UserInfoPageListView
     */
    PageResult<UserInfoPageListView> getUserInfoList(PageQry<UserInfoQueryApiParam> userInfoQueryApiParamPageQry);

    /**
     * 校验用户名的合法性
     * @param username 用户名
     * @return 是否合法
     */
    Boolean checkUsername(String username);

    /**
     * 校验邮箱合法性
     * @param email 邮箱
     * @return 是否合法
     */
    Boolean checkEmail(String email);

    /**
     * 删除用户
     * @param userIdList 用户id列表
     * @return IdsView
     */
    IdsView deleteUser(List<Long> userIdList);

    /**
     * 恢复用户
     * @param userIdList 用户列表
     * @return IdsView
     */
    IdsView recoverUser(List<Long> userIdList);

    /**
     * 添加角色
     * @param roleAddApiParam 角色请求参数
     * @return IdView
     */
    IdView addRole(RoleAddApiParam roleAddApiParam);

    /**
     * 角色分页查询
     * @param roleQueryApiParamPageQry 分页查询参数
     * @return PageResult<RolePageListView>
     */
    PageResult<RolePageListView> queryRoleByPage(PageQry<RoleQueryApiParam> roleQueryApiParamPageQry);

    /**
     * 更新角色
     * @param roleUpdateApiParam 角色更新参数
     * @return IdView
     */
    IdView updateRole(RoleUpdateApiParam roleUpdateApiParam);

    /**
     * 删除角色
     * @param roleIdList 角色列表
     * @return IdsView
     */
    IdsView deleteRole(List<Long> roleIdList);

    /**
     * 恢复角色
     * @param roleIdList 角色 id 列表
     * @return IdsView
     */
    IdsView recoverRole(List<Long> roleIdList);
}
