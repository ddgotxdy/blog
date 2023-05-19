package top.ddgotxdy.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.auth.convert.Entity2DTOConvert;
import top.ddgotxdy.auth.service.AuthQueryBizService;
import top.ddgotxdy.auth.service.BlogUserService;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.model.UserEmailCheckModel;
import top.ddgotxdy.common.model.auth.model.UserNameCheckModel;
import top.ddgotxdy.dal.entity.BlogUser;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class AuthQueryBizServiceImpl implements AuthQueryBizService {
    @Resource
    private BlogUserService blogUserService;

    @Override
    public UserInfoDTO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        BlogUser user = loginUser.getUser();
        UserInfoDTO userInfoDTO = Entity2DTOConvert.user2DTO(user);
        return userInfoDTO;
    }

    @Override
    public Boolean checkUserName(UserNameCheckModel userNameCheckModel) {
        if (Objects.isNull(userNameCheckModel)) {
            return false;
        }
        String username = userNameCheckModel.getUsername();
        if (Objects.isNull(username)) {
            return false;
        }
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogUser::getUsername, username);
        List<BlogUser> blogUsers = blogUserService.list(queryWrapper);
        return CollectionUtils.isEmpty(blogUsers);
    }

    @Override
    public Boolean checkEmail(UserEmailCheckModel userEmailCheckModel) {
        if (Objects.isNull(userEmailCheckModel)) {
            return false;
        }
        String email = userEmailCheckModel.getEmail();
        if (Objects.isNull(email)) {
            return false;
        }
        LambdaQueryWrapper<BlogUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(BlogUser::getEmail, email);
        List<BlogUser> blogUsers = blogUserService.list(queryWrapper);
        return CollectionUtils.isEmpty(blogUsers);
    }
}
