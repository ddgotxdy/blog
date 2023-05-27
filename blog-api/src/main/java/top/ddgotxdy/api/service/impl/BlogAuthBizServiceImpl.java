package top.ddgotxdy.api.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.api.convert.AuthApiParam2ClientParamConvert;
import top.ddgotxdy.api.convert.AuthDTO2ViewConvert;
import top.ddgotxdy.api.convert.SmsDTO2ViewConvert;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.MenuAddApiParam;
import top.ddgotxdy.api.model.addparam.RoleAddApiParam;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.MenuQueryApiParam;
import top.ddgotxdy.api.model.queryparam.RoleQueryApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.*;
import top.ddgotxdy.api.model.view.MenuPageListView;
import top.ddgotxdy.api.model.view.RolePageListView;
import top.ddgotxdy.api.model.view.UserInfoPageListView;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.api.service.BlogAuthBizService;
import top.ddgotxdy.common.client.BlogAuthClient;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.auth.addparam.MenuAddParam;
import top.ddgotxdy.common.model.auth.addparam.RoleAddParam;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.MenuDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.RoleDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
import top.ddgotxdy.common.model.auth.dto.MenuPageListDTO;
import top.ddgotxdy.common.model.auth.dto.RolePageListDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.queryparam.MenuQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.RoleQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;
import top.ddgotxdy.common.model.auth.recoverparam.MenuRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.RoleRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.UserDeleteParam;
import top.ddgotxdy.common.model.auth.updateparam.*;
import top.ddgotxdy.common.scope.ContextScope;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogAuthBizServiceImpl implements BlogAuthBizService {
    @Resource
    private BlogAuthClient blogAuthClient;

    @Override
    public IdView register(UserAddApiParam userAddApiParam) {
        UserAddParam userAddParam = AuthApiParam2ClientParamConvert.addApiParam2AddParam(userAddApiParam);
        ResultView<IdDTO> authResponse = blogAuthClient.register(userAddParam);
        return IdView.builder()
                .id(authResponse.checkAndGetData().getId())
                .build();
    }

    @Override
    public String login(UserLoginApiModel userLoginApiModel) {
        UserLoginModel userLoginModel = AuthApiParam2ClientParamConvert.apiParam2Param(userLoginApiModel);
        ResultView<String> response = blogAuthClient.login(userLoginModel);
        return response.checkAndGetData();
    }

    @Override
    public void logout() {
        Long userId = ContextScope.getUserId();
        ResultView response = blogAuthClient.logout(userId);
        response.checkAndGetData();
    }

    @Override
    public UserInfoView getUserInfo() {
        Long userId = ContextScope.getUserId();
        ResultView<UserInfoDTO> response = blogAuthClient.getUserInfo(userId);
        UserInfoView userInfoView = SmsDTO2ViewConvert.userInfoDTO2View(response.checkAndGetData());
        return userInfoView;
    }

    @Override
    public IdView updatePassword(UserPasswordUpdateApiParam userPasswordUpdateApiParam) {
        UserPasswordUpdateParam userPasswordUpdateParam
                = AuthApiParam2ClientParamConvert.apiParam2Param(userPasswordUpdateApiParam);
        ResultView<IdDTO> response = blogAuthClient.updatePassword(userPasswordUpdateParam);
        return IdView.builder()
                .id(response.checkAndGetData().getId())
                .build();
    }

    @Override
    public IdView updateEmail(UserEmailUpdateApiParam userEmailUpdateApiParam) {
        UserEmailUpdateParam userEmailUpdateParam
                = AuthApiParam2ClientParamConvert.apiParam2Param(userEmailUpdateApiParam);
        ResultView<IdDTO> response = blogAuthClient.updateEmail(userEmailUpdateParam);
        return IdView.builder()
                .id(response.checkAndGetData().getId())
                .build();
    }

    @Override
    public IdView updateUserInfo(UserInfoUpdateApiParam userInfoUpdateApiParam) {
        UserInfoUpdateParam userInfoUpdateParam
                = AuthApiParam2ClientParamConvert.apiParam2Param(userInfoUpdateApiParam);
        ResultView<IdDTO> response = blogAuthClient.updateUserInfo(userInfoUpdateParam);
        return IdView.builder()
                .id(response.checkAndGetData().getId())
                .build();
    }

    @Override
    public IdView updateUserRole(UserRoleUpdateApiParam userRoleUpdateApiParam) {
        UserRoleUpdateParam userRoleUpdateParam
                = AuthApiParam2ClientParamConvert.apiParam2Param(userRoleUpdateApiParam);
        ResultView<IdDTO> response = blogAuthClient.updateUserRole(userRoleUpdateParam);
        return IdView.builder()
                .id(response.checkAndGetData().getId())
                .build();
    }

    @Override
    public PageResult<UserInfoPageListView> getUserInfoList(PageQry<UserInfoQueryApiParam> userInfoQueryApiParamPageQry) {
        PageQry<UserInfoQueryParam> userInfoQueryParamPageQry
                = AuthApiParam2ClientParamConvert.queryApiParam2QueryParam(userInfoQueryApiParamPageQry);
        ResultView<PageResult<UserInfoPageListDTO>> response = blogAuthClient.getUserInfoList(userInfoQueryParamPageQry);
        PageResult<UserInfoPageListDTO> userInfoPageListDTOPageResult = response.checkAndGetData();
        PageResult<UserInfoPageListView> userInfoPageListViewPageResult
                = AuthDTO2ViewConvert.pageListDTO2View(userInfoPageListDTOPageResult);
        return userInfoPageListViewPageResult;
    }

    @Override
    public Boolean checkUsername(String username) {
        // 1. 构建请求参数
        PageQry<UserInfoQueryParam> userInfoQueryParamPageQry = new PageQry<>();
        UserInfoQueryParam userInfoQueryParam = new UserInfoQueryParam();
        userInfoQueryParamPageQry.setPageNum(1);
        userInfoQueryParamPageQry.setPageSize(10);
        userInfoQueryParam.setUsernameEq(username);
        userInfoQueryParamPageQry.setQueryParam(userInfoQueryParam);
        // 2. 调用接口
        ResultView<PageResult<UserInfoPageListDTO>> response = blogAuthClient.getUserInfoList(userInfoQueryParamPageQry);
        PageResult<UserInfoPageListDTO> userInfoPageListDTOPageResult = response.checkAndGetData();
        List<UserInfoPageListDTO> userInfoPageListDTOList = userInfoPageListDTOPageResult.getData();
        // 3. 获取当前用户的id
        Long userId = ContextScope.getUserId();
        // 4. 判断
        boolean anyMatch = userInfoPageListDTOList.stream()
                .anyMatch(userInfoPageListDTO -> !Objects.equals(userInfoPageListDTO.getUserId(), userId));
        return !anyMatch;
    }

    @Override
    public Boolean checkEmail(String email) {
        // 1. 构建请求参数
        PageQry<UserInfoQueryParam> userInfoQueryParamPageQry = new PageQry<>();
        UserInfoQueryParam userInfoQueryParam = new UserInfoQueryParam();
        userInfoQueryParamPageQry.setPageNum(1);
        userInfoQueryParamPageQry.setPageSize(10);
        userInfoQueryParam.setEmailEq(email);
        userInfoQueryParamPageQry.setQueryParam(userInfoQueryParam);
        // 2. 调用接口
        ResultView<PageResult<UserInfoPageListDTO>> response = blogAuthClient.getUserInfoList(userInfoQueryParamPageQry);
        PageResult<UserInfoPageListDTO> userInfoPageListDTOPageResult = response.checkAndGetData();
        List<UserInfoPageListDTO> userInfoPageListDTOList = userInfoPageListDTOPageResult.getData();
        // 3. 获取当前用户的id
        Long userId = ContextScope.getUserId();
        // 4. 判断
        boolean anyMatch = userInfoPageListDTOList.stream()
                .anyMatch(userInfoPageListDTO -> !Objects.equals(userInfoPageListDTO.getUserId(), userId));
        return !anyMatch;
    }

    @Override
    public IdsView deleteUser(List<Long> userIdList) {
        UserDeleteParam userDeleteParam
                = AuthApiParam2ClientParamConvert.deleteApiParam2DeleteParam(userIdList);
        ResultView<IdsDTO> response = blogAuthClient.deleteUser(userDeleteParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverUser(List<Long> userIdList) {
        UserRecoverParam userRecoverParam
                = AuthApiParam2ClientParamConvert.recoverApiParam2RecoverParam(userIdList);
        ResultView<IdsDTO> response = blogAuthClient.recoverUser(userRecoverParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdView addRole(RoleAddApiParam roleAddApiParam) {
        RoleAddParam roleAddParam
                = AuthApiParam2ClientParamConvert.addApiParam2AddParam(roleAddApiParam);
        ResultView<IdDTO> response = blogAuthClient.addRole(roleAddParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public PageResult<RolePageListView> queryRoleByPage(PageQry<RoleQueryApiParam> roleQueryApiParamPageQry) {
        PageQry<RoleQueryParam> roleQueryParamPageQry
                = AuthApiParam2ClientParamConvert.queryApiParam2RoleQueryParam(roleQueryApiParamPageQry);
        ResultView<PageResult<RolePageListDTO>> response = blogAuthClient.queryRoleByPage(roleQueryParamPageQry);
        PageResult<RolePageListDTO> rolePageListDTOPageResult = response.checkAndGetData();
        PageResult<RolePageListView> rolePageListViewPageResult
                = AuthDTO2ViewConvert.rolePageListDTO2View(rolePageListDTOPageResult);
        return rolePageListViewPageResult;
    }

    @Override
    public IdView updateRole(RoleUpdateApiParam roleUpdateApiParam) {
        RoleUpdateParam roleUpdateParam
                = AuthApiParam2ClientParamConvert.apiParam2Param(roleUpdateApiParam);
        ResultView<IdDTO> response = blogAuthClient.updateUserRole(roleUpdateParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdsView deleteRole(List<Long> roleIdList) {
        RoleDeleteParam roleDeleteParam
                = AuthApiParam2ClientParamConvert.deleteApiParam2RoleDeleteParam(roleIdList);
        ResultView<IdsDTO> response = blogAuthClient.deleteRole(roleDeleteParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverRole(List<Long> roleIdList) {
        RoleRecoverParam roleRecoverParam
                = AuthApiParam2ClientParamConvert.recoverApiParam2RoleRecoverParam(roleIdList);
        ResultView<IdsDTO> response = blogAuthClient.recoverRole(roleRecoverParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public String queryRoleById(Long roleId) {
        PageQry<RoleQueryParam> roleQueryParamPageQry = new PageQry<>();
        roleQueryParamPageQry.setPageNum(1);
        roleQueryParamPageQry.setPageSize(1);
        RoleQueryParam roleQueryParam = new RoleQueryParam();
        roleQueryParam.setRoleId(roleId);
        roleQueryParamPageQry.setQueryParam(roleQueryParam);
        ResultView<PageResult<RolePageListDTO>> response = blogAuthClient.queryRoleByPage(roleQueryParamPageQry);
        PageResult<RolePageListDTO> rolePageListDTOPageResult = response.checkAndGetData();
        List<RolePageListDTO> rolePageListDTOList = rolePageListDTOPageResult.getData();
        if (CollectionUtils.isEmpty(rolePageListDTOList)) {
            return "游客";
        }
        return rolePageListDTOList.get(0).getRoleName();
    }

    @Override
    public IdView addMenu(MenuAddApiParam menuAddApiParam) {
        MenuAddParam menuAddParam
                = AuthApiParam2ClientParamConvert.addApiParam2AddParam(menuAddApiParam);
        ResultView<IdDTO> response = blogAuthClient.addMenu(menuAddParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public PageResult<MenuPageListView> queryMenuByPage(PageQry<MenuQueryApiParam> menuQueryApiParamPageQry) {
        PageQry<MenuQueryParam> menuQueryParamPageQry
                = AuthApiParam2ClientParamConvert.queryApiParam2MenuQueryParam(menuQueryApiParamPageQry);
        ResultView<PageResult<MenuPageListDTO>> response = blogAuthClient.queryMenuByPage(menuQueryParamPageQry);
        PageResult<MenuPageListDTO> menuPageListDTOPageResult = response.checkAndGetData();
        PageResult<MenuPageListView> menuPageListViewPageResult
                = AuthDTO2ViewConvert.menuPageListDTO2View(menuPageListDTOPageResult);
        return menuPageListViewPageResult;
    }

    @Override
    public IdView updateMenu(MenuUpdateApiParam menuUpdateApiParam) {
        MenuUpdateParam menuUpdateParam
                = AuthApiParam2ClientParamConvert.apiParam2Param(menuUpdateApiParam);
        ResultView<IdDTO> response = blogAuthClient.updateMenu(menuUpdateParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdsView deleteMenu(List<Long> menuIdList) {
        MenuDeleteParam menuDeleteParam
                = AuthApiParam2ClientParamConvert.deleteApiParam2MenuDeleteParam(menuIdList);
        ResultView<IdsDTO> response = blogAuthClient.deleteMenu(menuDeleteParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverMenu(List<Long> menuIdList) {
        MenuRecoverParam menuRecoverParam
                = AuthApiParam2ClientParamConvert.recoverApiParam2MenuRecoverParam(menuIdList);
        ResultView<IdsDTO> response = blogAuthClient.recoverMenu(menuRecoverParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }
}