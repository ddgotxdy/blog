package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
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
}
