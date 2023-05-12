package top.ddgotxdy.auth.service;

import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;

/**
 * @author: ddgo
 * @description:
 */
public interface AuthCmdBizService {
    /**
     * 注册用户
     * @param userAddParam 用户添加参数
     * @return IdDTO
     */
    IdDTO register(UserAddParam userAddParam);

    /**
     * 登录
     * @param userLoginModel 登录请求参数
     * @return token
     */
    String login(UserLoginModel userLoginModel);

    /**
     * 登出
     */
    void logout();
}
