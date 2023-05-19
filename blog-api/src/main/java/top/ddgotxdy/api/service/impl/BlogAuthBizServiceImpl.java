package top.ddgotxdy.api.service.impl;

import org.springframework.stereotype.Service;
import top.ddgotxdy.api.convert.AuthApiParam2ClientParamConvert;
import top.ddgotxdy.api.convert.SmsDTO2ViewConvert;
import top.ddgotxdy.api.model.UserEmailCheckApiModel;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.UserNameCheckApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.api.service.BlogAuthBizService;
import top.ddgotxdy.common.client.BlogAuthClient;
import top.ddgotxdy.common.client.BlogSmsClient;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.model.UserEmailCheckModel;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.model.UserNameCheckModel;
import top.ddgotxdy.common.model.sms.queryparam.CaptchaQueryParam;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogAuthBizServiceImpl implements BlogAuthBizService {
    @Resource
    private BlogSmsClient blogSmsClient;
    @Resource
    private BlogAuthClient blogAuthClient;

    @Override
    public IdView register(UserAddApiParam userAddApiParam) {
        // 密码判断是否一致
        String password = userAddApiParam.getPassword();
        String rePassword = userAddApiParam.getRePassword();
        if (!Objects.equals(password, rePassword)) {
            throw new BlogException(ResultCode.PASSWORD_NOT_EQUAL);
        }
        // 验证码判断
        String email = userAddApiParam.getEmail();
        CaptchaQueryParam captchaQueryParam = new CaptchaQueryParam();
        captchaQueryParam.setMail(email);
        ResultView<String> response = blogSmsClient.queryCaptcha(captchaQueryParam);
        String captchaFromRedis = response.getData();
        String captcha = userAddApiParam.getCaptcha();
        if (!Objects.equals(captcha, captchaFromRedis)) {
            throw new BlogException(ResultCode.CAPTCHA_ERROR);
        }
        UserAddParam userAddParam = AuthApiParam2ClientParamConvert.addApiParam2AddParam(userAddApiParam);
        ResultView<IdDTO> authResponse = blogAuthClient.register(userAddParam);
        return IdView.builder()
                .id(authResponse.getData().getId())
                .build();
    }

    @Override
    public String login(UserLoginApiModel userLoginApiModel) {
        UserLoginModel userLoginModel = AuthApiParam2ClientParamConvert.apiParam2Param(userLoginApiModel);
        ResultView<String> response = blogAuthClient.login(userLoginModel);
        return response.getData();
    }

    @Override
    public void logout() {
        blogAuthClient.logout();
    }

    @Override
    public UserInfoView getUserInfo() {
        ResultView<UserInfoDTO> response = blogAuthClient.getUserInfo();
        UserInfoView userInfoView = SmsDTO2ViewConvert.userInfoDTO2View(response.getData());
        // 根据角色id获取角色名称 TODO
        userInfoView.setRoleName("普通用户");
        return userInfoView;
    }

    @Override
    public Boolean checkUserName(UserNameCheckApiModel userNameCheckApiModel) {
        UserNameCheckModel userNameCheckModel
                = AuthApiParam2ClientParamConvert.apiParam2Param(userNameCheckApiModel);
        ResultView<Boolean> response = blogAuthClient.checkUserName(userNameCheckModel);
        return response.getData();
    }

    @Override
    public Boolean checkEmail(UserEmailCheckApiModel userEmailCheckApiModel) {
        UserEmailCheckModel userEmailCheckModel
                = AuthApiParam2ClientParamConvert.apiParam2Param(userEmailCheckApiModel);
        ResultView<Boolean> response = blogAuthClient.checkEmail(userEmailCheckModel);
        return response.getData();
    }
}
