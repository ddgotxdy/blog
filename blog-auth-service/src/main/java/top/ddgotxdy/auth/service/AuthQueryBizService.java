package top.ddgotxdy.auth.service;

import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.model.UserEmailCheckModel;
import top.ddgotxdy.common.model.auth.model.UserNameCheckModel;

/**
 * @author: ddgo
 * @description:
 */
public interface AuthQueryBizService {
    /**
     * 获取用户信息
     * @return
     */
    UserInfoDTO getUserInfo();

    /**
     * 校验用户名是否合法
     * @param userNameCheckModel 用户名请求封装参数
     * @return Boolean
     */
    Boolean checkUserName(UserNameCheckModel userNameCheckModel);

    /**
     * 校验邮箱是否合法
     * @param userEmailCheckModel 邮箱请求封装参数
     * @return Boolean
     */
    Boolean checkEmail(UserEmailCheckModel userEmailCheckModel);
}
