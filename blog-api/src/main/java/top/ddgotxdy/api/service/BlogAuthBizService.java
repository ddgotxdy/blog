package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.UserEmailCheckApiModel;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.UserNameCheckApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.common.model.IdView;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogAuthBizService {
    /**
     * 注册
     * @param userAddApiParam
     * @return
     */
    IdView register(UserAddApiParam userAddApiParam);

    /**
     * 登录
     * @param userLoginApiModel
     * @return
     */
    String login(UserLoginApiModel userLoginApiModel);

    /**
     * 登出
     */
    void logout();

    /**
     * 获取用户基本信息
     * @return UserInfoView
     */
    UserInfoView getUserInfo();

    /**
     * 判断用户名是否合法
     * @param userNameCheckApiModel 用户名查询参数
     * @return Boolean
     */
    Boolean checkUserName(UserNameCheckApiModel userNameCheckApiModel);

    /**
     * 判断邮箱是否合法
     * @param userEmailCheckApiModel 邮箱查询参数
     * @return Boolean
     */
    Boolean checkEmail(UserEmailCheckApiModel userEmailCheckApiModel);
}
