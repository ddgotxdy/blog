package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.convert.Entity2DTOConvert;
import top.ddgotxdy.auth.convert.FieldName2FunctionConvert;
import top.ddgotxdy.auth.service.*;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.auth.dto.MenuPageListDTO;
import top.ddgotxdy.common.model.auth.dto.RolePageListDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.model.auth.queryparam.MenuQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.RoleQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;
import top.ddgotxdy.dal.entity.BlogMenu;
import top.ddgotxdy.dal.entity.BlogRole;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class AuthQueryBizServiceImpl implements AuthQueryBizService {
    @Resource
    private BlogUserService blogUserService;
    @Resource
    private BlogRoleService blogRoleService;
    @Resource
    private BlogMenuService blogMenuService;
    @Resource
    private BlogRoleMenuService blogRoleMenuService;

    @Override
    public UserInfoDTO getUserInfo(Long userId) {
        BlogUser blogUser = blogUserService.getById(userId);
        UserInfoDTO userInfoDTO = Entity2DTOConvert.user2DTO(blogUser);
        return userInfoDTO;
    }

    @Override
    public PageResult<UserInfoPageListDTO> getUserInfoList(PageQry<UserInfoQueryParam> userInfoQueryParamPageQry) {
        // 分页参数组装
        int pageNum = userInfoQueryParamPageQry.getPageNum();
        int pageSize = userInfoQueryParamPageQry.getPageSize();
        Page<BlogUser> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        UserInfoQueryParam queryParam = userInfoQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getUserId()), BlogUser::getUserId, queryParam.getUserId())
                .eq(Objects.nonNull(queryParam.getUsernameEq()), BlogUser::getUsername, queryParam.getUsernameEq())
                .eq(Objects.nonNull(queryParam.getEmailEq()), BlogUser::getEmail, queryParam.getEmailEq())
                .eq(Objects.nonNull(queryParam.getSex()), BlogUser::getSex, queryParam.getSex())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogUser::getIsDelete, queryParam.getIsDelete())
                .like(Objects.nonNull(queryParam.getUsername()), BlogUser::getUsername, queryParam.getUsername())
                .like(Objects.nonNull(queryParam.getEmail()), BlogUser::getEmail, queryParam.getEmail());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = userInfoQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.userFiledName2Function(name))
        );
        Page<BlogUser> blogUserPage = blogUserService.page(page, queryWrapper);
        List<BlogUser> blogUserList = blogUserPage.getRecords();
        List<UserInfoPageListDTO> userInfoPageListDTOList = Entity2DTOConvert.userList2DTO(blogUserList);
        // 封装返回值
        PageResult<UserInfoPageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogUserPage.getPages());
        pageResult.setData(userInfoPageListDTOList);
        return pageResult;
    }

    @Override
    public PageResult<RolePageListDTO> queryRoleByPage(PageQry<RoleQueryParam> roleQueryParamPageQry) {
        // 分页参数组装
        int pageNum = roleQueryParamPageQry.getPageNum();
        int pageSize = roleQueryParamPageQry.getPageSize();
        Page<BlogRole> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogRole> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        RoleQueryParam queryParam = roleQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getRoleId()), BlogRole::getRoleId, queryParam.getRoleId())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogRole::getIsDelete, queryParam.getIsDelete())
                .like(Objects.nonNull(queryParam.getRoleName()), BlogRole::getRoleName, queryParam.getRoleName());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = roleQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.RoleFiledName2Function(name))
        );
        Page<BlogRole> blogRolePage = blogRoleService.page(page, queryWrapper);
        List<BlogRole> blogRoleList = blogRolePage.getRecords();
        List<RolePageListDTO> rolePageListDTOList = Entity2DTOConvert.roleList2DTO(blogRoleList);
        // 去获取当前角色对应的菜单
        rolePageListDTOList.forEach(rolePageListDTO -> {
            Long roleId = rolePageListDTO.getRoleId();
            List<Long> menuIdList = blogRoleMenuService.queryMenuIdListByRoleId(roleId);
            rolePageListDTO.setMenuIds(menuIdList);
        });
        // 封装返回值
        PageResult<RolePageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogRolePage.getPages());
        pageResult.setData(rolePageListDTOList);
        return pageResult;
    }

    @Override
    public PageResult<MenuPageListDTO> queryMenuByPage(
            PageQry<MenuQueryParam> menuQueryParamPageQry
    ) {
        // 分页参数组装【只对起始节点】
        int pageNum = menuQueryParamPageQry.getPageNum();
        int pageSize = menuQueryParamPageQry.getPageSize();
        Page<BlogMenu> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogMenu> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        MenuQueryParam queryParam = menuQueryParamPageQry.getQueryParam();
        queryWrapper
                .isNull(BlogMenu::getParentId)
                .eq(Objects.nonNull(queryParam.getMenuId()), BlogMenu::getMenuId, queryParam.getMenuId())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogMenu::getIsDelete, queryParam.getIsDelete())
                .like(Objects.nonNull(queryParam.getMenuName()), BlogMenu::getMenuName, queryParam.getMenuName())
                .like(Objects.nonNull(queryParam.getPath()), BlogMenu::getPath, queryParam.getPath())
                .like(Objects.nonNull(queryParam.getComponent()), BlogMenu::getComponent, queryParam.getComponent())
                .like(Objects.nonNull(queryParam.getIcon()), BlogMenu::getIcon, queryParam.getIcon());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = menuQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.menuFiledName2Function(name))
        );
        Page<BlogMenu> blogMenuPage = blogMenuService.page(page, queryWrapper);
        List<BlogMenu> blogMenuList = blogMenuPage.getRecords();
        // 非起始节点
        LambdaQueryWrapper<BlogMenu> queryWrapperChildren = new LambdaQueryWrapper<>();
        queryWrapperChildren
                .isNotNull(BlogMenu::getParentId)
                .eq(Objects.nonNull(queryParam.getMenuId()), BlogMenu::getMenuId, queryParam.getMenuId())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogMenu::getIsDelete, queryParam.getIsDelete())
                .like(Objects.nonNull(queryParam.getMenuName()), BlogMenu::getMenuName, queryParam.getMenuName())
                .like(Objects.nonNull(queryParam.getPath()), BlogMenu::getPath, queryParam.getPath())
                .like(Objects.nonNull(queryParam.getComponent()), BlogMenu::getComponent, queryParam.getComponent())
                .like(Objects.nonNull(queryParam.getIcon()), BlogMenu::getIcon, queryParam.getIcon());
        List<BlogMenu> blogMenuListChildren = blogMenuService.list(queryWrapperChildren);
        List<MenuPageListDTO> rolePageListDTOList
                = Entity2DTOConvert.menuList2DTO(blogMenuList, blogMenuListChildren);
        // 封装返回值
        PageResult<MenuPageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogMenuPage.getPages());
        pageResult.setData(rolePageListDTOList);
        return pageResult;
    }
}
