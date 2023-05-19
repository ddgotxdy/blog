package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.ddgotxdy.common.interceptor.FeignRequestInterceptor;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.model.UserEmailCheckModel;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.model.UserNameCheckModel;

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
     * 检查用户名是否合法
     * @param userNameCheckModel 用户名校验参数
     * @return Boolean
     */
    @PostMapping("openfeign/auth/checkUserName")
    ResultView<Boolean> checkUserName(
            @Validated @RequestBody UserNameCheckModel userNameCheckModel
    );

    /**
     * 检查邮箱是否合法
     * @param userEmailCheckModel 邮箱校验参数
     * @return Boolean
     */
    @PostMapping("openfeign/auth/checkEmail")
    ResultView<Boolean> checkEmail(
            @Validated @RequestBody UserEmailCheckModel userEmailCheckModel
    );
}
