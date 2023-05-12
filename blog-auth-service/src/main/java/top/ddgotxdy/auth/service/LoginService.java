package top.ddgotxdy.auth.service;

import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;

/**
 * @author: ddgo
 * @description:
 */
public interface LoginService {

    /**
     * 登录
     * @param userLoginModel 登录请求参数
     * @return String
     */
    String login(UserLoginModel userLoginModel);

    /**
     * 用户退出
     * @return ResultView
     */
    void logout();
}
