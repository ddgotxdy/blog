package top.ddgotxdy.api.service.impl;

import org.springframework.stereotype.Service;
import top.ddgotxdy.api.convert.AuthApiParam2ClientParamConvert;
import top.ddgotxdy.api.convert.SmsDTO2ViewConvert;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.updateparam.UserEmailUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserInfoUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserPasswordUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserRoleUpdateApiParam;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.api.service.BlogAuthBizService;
import top.ddgotxdy.common.client.BlogAuthClient;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.updateparam.UserEmailUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserInfoUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserPasswordUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserRoleUpdateParam;

import javax.annotation.Resource;

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
}
