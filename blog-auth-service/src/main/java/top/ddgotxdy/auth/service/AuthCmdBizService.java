package top.ddgotxdy.auth.service;

import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.updateparam.UserEmailUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserInfoUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserPasswordUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserRoleUpdateParam;

/**
 * @author: ddgo
 * @description:
 */
public interface AuthCmdBizService {
    /**
     * 注册用户
     * @param userAddParam 用户添加参数
     * @return IdDTO
     */
    IdDTO register(UserAddParam userAddParam);

    /**
     * 登录
     * @param userLoginModel 登录请求参数
     * @return token
     */
    String login(UserLoginModel userLoginModel);

    /**
     * 登出
     */
    void logout();

    /**
     * 修改用户密码
     * @param userPasswordUpdateParam 密码修改参数
     * @return IdDTO
     */
    IdDTO updatePassword(UserPasswordUpdateParam userPasswordUpdateParam);

    /**
     * 修改用户邮箱
     * @param userEmailUpdateParam 修改邮箱参数
     * @return IdDTO
     */
    IdDTO updateEmail(UserEmailUpdateParam userEmailUpdateParam);

    /**
     * 用户基本信息修改
     * @param userInfoUpdateParam 用户基本信息修改参数
     * @return IdDTO
     */
    IdDTO updateUserInfo(UserInfoUpdateParam userInfoUpdateParam);

    /**
     * 用户角色信息修改
     * @param userRoleUpdateParam 用户角色信息修改参数
     * @return IdDTO
     */
    IdDTO updateRole(UserRoleUpdateParam userRoleUpdateParam);
}
