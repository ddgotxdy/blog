package top.ddgotxdy.auth.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import top.ddgotxdy.auth.convert.Entity2DTOConvert;
import top.ddgotxdy.auth.service.AuthQueryBizService;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.dal.entity.BlogUser;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class AuthQueryBizServiceImpl implements AuthQueryBizService {
    @Override
    public UserInfoDTO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        BlogUser user = loginUser.getUser();
        UserInfoDTO userInfoDTO = Entity2DTOConvert.user2DTO(user);
        return userInfoDTO;
    }
}
