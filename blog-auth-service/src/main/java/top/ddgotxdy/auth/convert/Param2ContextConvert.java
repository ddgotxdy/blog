package top.ddgotxdy.auth.convert;

import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.util.BeanCopyUtil;

/**
 * @author: ddgo
 * @description:
 */
public class Param2ContextConvert {
    private Param2ContextConvert() { }
    public static AuthContext addParamConvert(UserAddParam userAddParam) {
        AuthContext authContext = new AuthContext();
        BeanCopyUtil.copyProperties(userAddParam, authContext);
        // 设置事件
        authContext.setAuthEvent(AuthEvent.REGISTER);
        return authContext;
    }
}
