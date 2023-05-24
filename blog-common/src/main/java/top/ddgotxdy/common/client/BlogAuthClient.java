package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.ddgotxdy.common.interceptor.FeignRequestInterceptor;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;
import top.ddgotxdy.common.model.auth.recoverparam.UserDeleteParam;
import top.ddgotxdy.common.model.auth.updateparam.UserEmailUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserInfoUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserPasswordUpdateParam;
import top.ddgotxdy.common.model.auth.updateparam.UserRoleUpdateParam;

/**
 * @author: ddgo
 * @description:
 */
@Component
@FeignClient(value = "auth-service", configuration = FeignRequestInterceptor.class)
public interface BlogAuthClient {
    /**
     * 注册
     * @param userAddParam 注册参数
     * @return IdDTO
     */
    @PostMapping("openfeign/auth/register")
    ResultView<IdDTO> register(
            @Validated @RequestBody UserAddParam userAddParam
    );

    /**
     * 登录
     * @param userLoginModel 登录参数
     * @return token
     */
    @PostMapping("openfeign/auth/login")
    ResultView<String> login(
            @Validated @RequestBody UserLoginModel userLoginModel
    );

    /**
     * 登出
     * @return void
     */
    @PostMapping("openfeign/auth/logout")
    ResultView logout();

    /**
     * 获取用户信息接口
     * @return UserInfoDTO
     */
    @PostMapping("openfeign/auth/getUserInfo")
    ResultView<UserInfoDTO> getUserInfo();

    /**
     * 更新密码
     * @param userPasswordUpdateParam 更新密码参数
     * @return IdDTO
     */
    @PostMapping("openfeign/auth/updatePassword")
    ResultView<IdDTO> updatePassword(
            @Validated @RequestBody UserPasswordUpdateParam userPasswordUpdateParam
    );

    /**
     * 更新邮箱
     * @param userEmailUpdateParam 更新邮箱参数
     * @return IdDTO
     */
    @PostMapping("openfeign/auth/updateEmail")
    ResultView<IdDTO> updateEmail(
            @Validated @RequestBody UserEmailUpdateParam userEmailUpdateParam
    );

    /**
     * 更新用户信息
     * @param userInfoUpdateParam 更新用户信息参数
     * @return IdDTO
     */
    @PostMapping("openfeign/auth/updateUserInfo")
    ResultView<IdDTO> updateUserInfo(
            @Validated @RequestBody UserInfoUpdateParam userInfoUpdateParam
    );

    /**
     * 更新用户角色
     * @param userRoleUpdateParam 更新用户角色参数
     * @return IdDTO
     */
    @PostMapping("openfeign/auth/updateUserRole")
    ResultView<IdDTO> updateRole(
            @Validated @RequestBody UserRoleUpdateParam userRoleUpdateParam
    );

    /**
     * 分页查询用户基本信息
     * @param userInfoQueryParamPageQry 分页查询参数
     * @return PageResult<UserInfoPageListDTO>
     */
    @PostMapping("openfeign/auth/getUserInfoList")
    ResultView<PageResult<UserInfoPageListDTO>> getUserInfoList(
            @Validated @RequestBody PageQry<UserInfoQueryParam> userInfoQueryParamPageQry
    );

    /**
     * 删除用户信息
     * @param userDeleteParam 用户删除参数
     * @return IdsDTO
     */
    @DeleteMapping("openfeign/auth/deleteUser")
    ResultView<IdsDTO> deleteUser(
            @Validated @RequestBody UserDeleteParam userDeleteParam
    );

    /**
     * 恢复用户信息
     * @param userRecoverParam 用户恢复参数
     * @return IdsDTO
     */
    @PostMapping("openfeign/auth/recoverUser")
    ResultView<IdsDTO> recoverUser(
            @Validated @RequestBody UserRecoverParam userRecoverParam
    );
}
