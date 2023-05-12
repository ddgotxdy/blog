package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.adaptor.AuthManageAdaptor;
import top.ddgotxdy.auth.convert.Param2ContextConvert;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.service.AuthCmdBizService;
import top.ddgotxdy.auth.service.LoginService;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;

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
}
