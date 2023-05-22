package top.ddgotxdy.api.service.impl;

import org.springframework.stereotype.Service;
import top.ddgotxdy.api.convert.AuthApiParam2ClientParamConvert;
import top.ddgotxdy.api.convert.AuthDTO2ViewConvert;
import top.ddgotxdy.api.convert.SmsDTO2ViewConvert;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.UserEmailUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserInfoUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserPasswordUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserRoleUpdateApiParam;
import top.ddgotxdy.api.model.view.UserInfoPageListView;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.api.service.BlogAuthBizService;
import top.ddgotxdy.common.client.BlogAuthClient;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;
import top.ddgotxdy.common.model.auth.updateparam.UserEmailUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserInfoUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserPasswordUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserRoleUpdateParam;
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
        ResultView response = blogAuthClient.logout();
        response.checkAndGetData();
    }

    @Override
    public UserInfoView getUserInfo() {
        ResultView<UserInfoDTO> response = blogAuthClient.getUserInfo();
        UserInfoView userInfoView = SmsDTO2ViewConvert.userInfoDTO2View(response.checkAndGetData());
        // 根据角色id获取角色名称 TODO
        userInfoView.setRoleName("普通用户");
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
    public IdView updateRole(UserRoleUpdateApiParam userRoleUpdateApiParam) {
        UserRoleUpdateParam userRoleUpdateParam
                = AuthApiParam2ClientParamConvert.apiParam2Param(userRoleUpdateApiParam);
        ResultView<IdDTO> response = blogAuthClient.updateRole(userRoleUpdateParam);
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
        // Role name 赋值
        List<UserInfoPageListView> data = userInfoPageListViewPageResult.getData();
        for (int i = 0; i < data.size(); i++) {
            UserInfoPageListView userInfoPageListView = data.get(i);
            // 根据角色id获取角色名称 TODO
            userInfoPageListView.setRoleName("普通用户");
        }
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
        userInfoQueryParam.setEmail(email);
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
}
