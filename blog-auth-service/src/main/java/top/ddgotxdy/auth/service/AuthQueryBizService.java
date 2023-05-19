package top.ddgotxdy.auth.service;

import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;

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
}
