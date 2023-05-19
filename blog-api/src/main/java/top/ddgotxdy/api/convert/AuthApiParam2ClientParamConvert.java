package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.UserEmailCheckApiModel;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.UserNameCheckApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.model.UserEmailCheckModel;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.model.UserNameCheckModel;
import top.ddgotxdy.common.util.BeanCopyUtil;

/**
 * @author: ddgo
 * @description:
 */
public class AuthApiParam2ClientParamConvert {
    private AuthApiParam2ClientParamConvert() { }
    public static UserAddParam addApiParam2AddParam(UserAddApiParam userAddApiParam) {
        UserAddParam userAddParam = new UserAddParam();
        BeanCopyUtil.copyProperties(userAddApiParam, userAddParam);
        return userAddParam;
    }

    public static UserLoginModel apiParam2Param(UserLoginApiModel userLoginApiModel) {
        UserLoginModel userLoginModel = new UserLoginModel();
        BeanCopyUtil.copyProperties(userLoginApiModel, userLoginModel);
        return userLoginModel;
    }

    public static UserNameCheckModel apiParam2Param(UserNameCheckApiModel userNameCheckApiModel) {
        UserNameCheckModel userNameCheckModel = new UserNameCheckModel();
        BeanCopyUtil.copyProperties(userNameCheckApiModel, userNameCheckModel);
        return userNameCheckModel;
    }

    public static UserEmailCheckModel apiParam2Param(UserEmailCheckApiModel userEmailCheckApiModel) {
        UserEmailCheckModel userEmailCheckModel = new UserEmailCheckModel();
        BeanCopyUtil.copyProperties(userEmailCheckApiModel, userEmailCheckModel);
        return userEmailCheckModel;
    }
}
