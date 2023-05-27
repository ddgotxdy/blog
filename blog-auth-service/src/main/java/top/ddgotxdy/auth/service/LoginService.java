package top.ddgotxdy.auth.service;

import top.ddgotxdy.common.model.auth.model.UserLoginModel;

import java.util.List;

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
    void logout(Long userId);

    /**
     * 刷新登录redis中的信息
     * @param userId 用户id
     */
    void refreshById(Long userId);

    /**
     * 批量刷新登录redis中的信息
     * @param userIdList id列表
     */
    void refreshByBatchIds(List<Long> userIdList);
}
