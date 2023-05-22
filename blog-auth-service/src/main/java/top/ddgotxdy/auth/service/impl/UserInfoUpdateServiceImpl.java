package top.ddgotxdy.auth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.auth.annotation.AuthEventSelector;
import top.ddgotxdy.auth.convert.Context2EntityConvert;
import top.ddgotxdy.auth.model.AuthContext;
import top.ddgotxdy.auth.model.AuthEvent;
import top.ddgotxdy.auth.service.AbstractAuthService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.Objects;

import static top.ddgotxdy.auth.constant.ValidateConstant.*;

/**
 * @author: ddgo
 * @description:
 */
@AuthEventSelector(AuthEvent.USER_INFO_UPDATE)
@Service
@Slf4j
public class UserInfoUpdateServiceImpl extends AbstractAuthService {
    @Resource
    private BlogUserService blogUserService;

    @Override
    protected boolean filter(AuthContext authContext) {
        // 1. user id 校验
        Long userId = authContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.USER_INFO_UPDATE_ERROR.getCode(), "用户id为空");
        }
        // 2. 用户名唯一
        if (!uniqueUsername(authContext)) {
            throw new BlogException(ResultCode.USER_INFO_UPDATE_ERROR.getCode(), "用户名存在");
        }
        // 3. 用户名长度校验
        String username = authContext.getUsername();
        if (StringUtils.isNotEmpty(username)
                && (StringUtils.length(username) < USERNAME_MIN_LENGTH
                || StringUtils.length(username) > USERNAME_MAX_LENGTH)) {
            throw new BlogException(ResultCode.USER_INFO_UPDATE_ERROR.getCode(), "用户名长度错误");
        }
        // 4. 用户真实名称长度校验
        String nickname = authContext.getNickname();
        if (StringUtils.isNotEmpty(nickname)
                && (StringUtils.length(nickname) < NICKNAME_MIN_LENGTH
                || StringUtils.length(nickname) > NICKNAME_MAX_LENGTH)) {
            throw new BlogException(ResultCode.USER_INFO_UPDATE_ERROR.getCode(), "真实名称长度错误");
        }
        // 5. 手机号合法性校验
        String phoneNumber = authContext.getPhoneNumber();
        if (StringUtils.isNotEmpty(phoneNumber)
                && StringUtils.length(phoneNumber) != PHONE_NUMBER_LENGTH) {
            throw new BlogException(ResultCode.USER_INFO_UPDATE_ERROR.getCode(), "手机号长度错误");
        }
        // 6. 头像链接长度校验
        String avatarUrl = authContext.getAvatarUrl();
        if (StringUtils.isNotEmpty(avatarUrl)
                && StringUtils.length(avatarUrl) > AVATAR_URL_MAX_LENGTH) {
            throw new BlogException(ResultCode.USER_INFO_UPDATE_ERROR.getCode(), "头像地址长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(AuthContext authContext) {
        BlogUser blogUser = Context2EntityConvert.authContext2UserForUpdate(authContext);
        blogUserService.updateById(blogUser);
        // 更新redis里面的信息
        updateUserInfoFromRedis(authContext);
    }
}
