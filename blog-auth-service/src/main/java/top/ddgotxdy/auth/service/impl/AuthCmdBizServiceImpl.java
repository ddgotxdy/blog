package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.adaptor.AuthManageAdaptor;
import top.ddgotxdy.auth.convert.Param2ContextConvert;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.service.AuthCmdBizService;
import top.ddgotxdy.auth.service.LoginService;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdsDTO;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.recoverparam.UserDeleteParam;
import top.ddgotxdy.common.model.auth.updateparam.UserEmailUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserInfoUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserPasswordUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserRoleUpdateParam;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @author: ddgo
 * @description:
 */
@Service
@Slf4j
public class AuthCmdBizServiceImpl implements AuthCmdBizService {
    @Resource
    private AuthManageAdaptor authManageAdaptor;
    @Resource
    private LoginService loginService;

    @Override
    public IdDTO register(UserAddParam userAddParam) {
        AuthContext authContext = Param2ContextConvert.addParamConvert(userAddParam);
        log.info("AuthCmdBizServiceImpl register request[{}]", toJSON(authContext));
        authManageAdaptor.execute(authContext);
        return IdDTO.builder()
                .id(authContext.getUserId())
                .build();
    }

    @Override
    public String login(UserLoginModel userLoginModel) {
        return loginService.login(userLoginModel);
    }

    @Override
    public void logout() {
        loginService.logout();
    }

    @Override
    public IdDTO updatePassword(UserPasswordUpdateParam userPasswordUpdateParam) {
        AuthContext authContext = Param2ContextConvert.updateParamConvert(userPasswordUpdateParam);
        log.info("AuthCmdBizServiceImpl updatePassword request[{}]", toJSON(authContext));
        authManageAdaptor.execute(authContext);
        return IdDTO.builder()
                .id(authContext.getUserId())
                .build();
    }

    @Override
    public IdDTO updateEmail(UserEmailUpdateParam userEmailUpdateParam) {
        AuthContext authContext = Param2ContextConvert.updateParamConvert(userEmailUpdateParam);
        log.info("AuthCmdBizServiceImpl updateEmail request[{}]", toJSON(authContext));
        authManageAdaptor.execute(authContext);
        return IdDTO.builder()
                .id(authContext.getUserId())
                .build();
    }

    @Override
    public IdDTO updateUserInfo(UserInfoUpdateParam userInfoUpdateParam) {
        AuthContext authContext = Param2ContextConvert.updateParamConvert(userInfoUpdateParam);
        log.info("AuthCmdBizServiceImpl updateUserInfo request[{}]", toJSON(authContext));
        authManageAdaptor.execute(authContext);
        return IdDTO.builder()
                .id(authContext.getUserId())
                .build();
    }

    @Override
    public IdDTO updateRole(UserRoleUpdateParam userRoleUpdateParam) {
        AuthContext authContext = Param2ContextConvert.updateParamConvert(userRoleUpdateParam);
        log.info("AuthCmdBizServiceImpl updateRole request[{}]", toJSON(authContext));
        authManageAdaptor.execute(authContext);
        return IdDTO.builder()
                .id(authContext.getUserId())
                .build();
    }

    @Override
    public IdsDTO deleteUser(UserDeleteParam userDeleteParam) {
        AuthContext authContext = Param2ContextConvert.deleteParamConvert(userDeleteParam);
        log.info("AuthCmdBizServiceImpl deleteUser request[{}]", toJSON(authContext));
        authManageAdaptor.execute(authContext);
        return IdsDTO.builder()
                .ids(authContext.getUserIds())
                .build();
    }

    @Override
    public IdsDTO recoverUser(UserRecoverParam userRecoverParam) {
        AuthContext authContext = Param2ContextConvert.recoverParamConvert(userRecoverParam);
        log.info("AuthCmdBizServiceImpl recoverUser request[{}]", toJSON(authContext));
        authManageAdaptor.execute(authContext);
        return IdsDTO.builder()
                .ids(authContext.getUserIds())
                .build();
    }
}
