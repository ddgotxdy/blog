package top.ddgotxdy.auth.service;

import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdsDTO;
import top.ddgotxdy.common.model.auth.addparam.MenuAddParam;
import top.ddgotxdy.common.model.auth.addparam.ResourceAddParam;
import top.ddgotxdy.common.model.auth.addparam.RoleAddParam;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.MenuDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.ResourceDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.RoleDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.recoverparam.MenuRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.ResourceRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.RoleRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.UserDeleteParam;
import top.ddgotxdy.common.model.auth.updateparam.*;

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
     * @param userId 用户id
     */
    void logout(Long userId);

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
    IdDTO updateUserRole(UserRoleUpdateParam userRoleUpdateParam);

    /**
     * 删除用户
     * @param userDeleteParam 删除用户参数
     * @return IdsDTO
     */
    IdsDTO deleteUser(UserDeleteParam userDeleteParam);

    /**
     * 恢复用户
     * @param userRecoverParam 恢复用户参数
     * @return IdsDTO
     */
    IdsDTO recoverUser(UserRecoverParam userRecoverParam);

    /**
     * 添加角色
     * @param roleAddParam 角色添加参数
     * @return IdDTO
     */
    IdDTO addRole(RoleAddParam roleAddParam);

    /**
     * 更新角色
     * @param roleUpdateParam 角色更新参数
     * @return IdDTO
     */
    IdDTO updateRole(RoleUpdateParam roleUpdateParam);

    /**
     * 删除角色
     * @param roleDeleteParam 角色删除参数
     * @return IdsDTO
     */
    IdsDTO deleteRole(RoleDeleteParam roleDeleteParam);

    /**
     * 恢复角色
     * @param roleRecoverParam 角色恢复参数
     * @return IdsDTO
     */
    IdsDTO recoverRole(RoleRecoverParam roleRecoverParam);

    /**
     * 添加菜单
     * @param menuAddParam 菜单添加参数
     * @return IdDTO
     */
    IdDTO addMenu(MenuAddParam menuAddParam);

    /**
     * 更新菜单
     * @param menuUpdateParam 更新菜单参数
     * @return IdDTO
     */
    IdDTO updateMenu(MenuUpdateParam menuUpdateParam);

    /**
     * 删除菜单
     * @param menuDeleteParam 删除菜单参数
     * @return IdsDTO
     */
    IdsDTO deleteMenu(MenuDeleteParam menuDeleteParam);

    /**
     * 恢复菜单
     * @param menuRecoverParam 菜单恢复参数
     * @return IdsDTO
     */
    IdsDTO recoverMenu(MenuRecoverParam menuRecoverParam);

    IdDTO addResource(ResourceAddParam resourceAddParam);

    IdDTO updateResource(ResourceUpdateParam resourceUpdateParam);

    IdsDTO deleteResource(ResourceDeleteParam resourceDeleteParam);

    IdsDTO recoverResource(ResourceRecoverParam resourceRecoverParam);
}
