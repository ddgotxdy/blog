package top.ddgotxdy.auth.service;

import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.auth.dto.MenuPageListDTO;
import top.ddgotxdy.common.model.auth.dto.RolePageListDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.model.auth.queryparam.MenuQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.RoleQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;

/**
 * @author: ddgo
 * @description:
 */
public interface AuthQueryBizService {
    /**
     * 获取用户信息
     * @param userId 用户 id
     * @return UserInfoDTO
     */
    UserInfoDTO getUserInfo(Long userId);

    /**
     * 分页获取用户信息
     * @param userInfoQueryParamPageQry 用户分页请求参数
     * @return PageResult<UserInfoDTO>
     */
    PageResult<UserInfoPageListDTO> getUserInfoList(PageQry<UserInfoQueryParam> userInfoQueryParamPageQry);

    /**
     * 分页查询角色
     * @param roleQueryParamPageQry 角色分页请求参数
     * @return PageResult<RolePageListDTO>
     */
    PageResult<RolePageListDTO> queryRoleByPage(PageQry<RoleQueryParam> roleQueryParamPageQry);

    /**
     * 分页查询菜单
     * @param menuQueryParamPageQry 分页查询菜单参数
     * @return PageResult<MenuPageListDTO>
     */
    PageResult<MenuPageListDTO> queryMenuByPage(PageQry<MenuQueryParam> menuQueryParamPageQry);
}
