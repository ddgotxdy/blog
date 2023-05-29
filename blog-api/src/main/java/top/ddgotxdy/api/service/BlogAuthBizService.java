package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.MenuAddApiParam;
import top.ddgotxdy.api.model.addparam.ResourceAddApiParam;
import top.ddgotxdy.api.model.addparam.RoleAddApiParam;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.MenuQueryApiParam;
import top.ddgotxdy.api.model.queryparam.ResourceQueryApiParam;
import top.ddgotxdy.api.model.queryparam.RoleQueryApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.*;
import top.ddgotxdy.api.model.view.*;
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
     * @param userAddApiParam 用户新增参数
     * @return IdView
     */
    IdView register(UserAddApiParam userAddApiParam);

    /**
     * 登录
     * @param userLoginApiModel 登录参数
     * @return token
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

    /**
     * 获取角色名称
     * @param roleId 角色id
     * @return String
     */
    String queryRoleById(Long roleId);

    /**
     * 菜单添加
     * @param menuAddApiParam 菜单添加参数
     * @return IdView
     */
    IdView addMenu(MenuAddApiParam menuAddApiParam);

    /**
     * 菜单分页查询
     * @param menuQueryApiParamPageQry 菜单分页查询参数
     * @return PageResult<MenuPageListView>
     */
    PageResult<MenuPageListView> queryMenuByPage(PageQry<MenuQueryApiParam> menuQueryApiParamPageQry);

    /**
     * 菜单更新
     * @param menuUpdateApiParam 菜单更新参数
     * @return IdView
     */
    IdView updateMenu(MenuUpdateApiParam menuUpdateApiParam);

    /**
     * 菜单删除
     * @param menuIdList 菜单id列表
     * @return IdsView
     */
    IdsView deleteMenu(List<Long> menuIdList);

    /**
     * 菜单恢复
     * @param menuIdList 菜单id列表
     * @return IdsView
     */
    IdsView recoverMenu(List<Long> menuIdList);

    /**
     * 添加资源
     * @param resourceAddApiParam 添加资源参数
     * @return IdView
     */
    IdView addResource(ResourceAddApiParam resourceAddApiParam);

    /**
     * 分页查询资源
     * @param resourceQueryApiParamPageQry 分页查询资源参数
     * @return PageResult<ResourcePageListView>
     */
    PageResult<ResourcePageListView> queryResourceByPage(PageQry<ResourceQueryApiParam> resourceQueryApiParamPageQry);

    /**
     * 更新资源
     * @param resourceUpdateApiParam 更新资源参数
     * @return IdView
     */
    IdView updateResource(ResourceUpdateApiParam resourceUpdateApiParam);

    /**
     * 删除资源
     * @param resourceIdList 资源列表
     * @return IdView
     */
    IdsView deleteResource(List<Long> resourceIdList);

    /**
     * 恢复资源
     * @param resourceIdList 资源列表
     * @return IdView
     */
    IdsView recoverResource(List<Long> resourceIdList);
}
