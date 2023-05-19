package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ddgotxdy.api.model.UserEmailCheckApiModel;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.UserNameCheckApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.api.service.BlogAuthBizService;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.ResultView;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description:
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "授权接口")
public class AuthController {
    @Resource
    private BlogAuthBizService blogAuthBizService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResultView<IdView> register(
            @Validated @RequestBody UserAddApiParam userAddApiParam
    ) {
        IdView idView = blogAuthBizService.register(userAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultView<String> login(
            @Validated @RequestBody UserLoginApiModel userLoginApiModel
    ) {
        String token = blogAuthBizService.login(userLoginApiModel);
        return ResultView.success(token);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public ResultView logout() {
        blogAuthBizService.logout();
        return ResultView.success();
    }

    @ApiOperation("获取用户信息")
    @PostMapping("/getUserInfo")
    public ResultView<UserInfoView> getUserInfo() {
        UserInfoView userInfoView = blogAuthBizService.getUserInfo();
        return ResultView.success(userInfoView);
    }

    @ApiOperation("检查用户名是否合法")
    @PostMapping("/checkUserName")
    public ResultView<Boolean> checkUserName(
            @Validated @RequestBody UserNameCheckApiModel userNameCheckApiModel
    ) {
        Boolean ok = blogAuthBizService.checkUserName(userNameCheckApiModel);
        return ResultView.success(ok);
    }

    @ApiOperation("检查邮箱是否合法")
    @PostMapping("/checkEmail")
    public ResultView<Boolean> checkEmail(
            @Validated @RequestBody UserEmailCheckApiModel userEmailCheckApiModel
    ) {
        Boolean ok = blogAuthBizService.checkEmail(userEmailCheckApiModel);
        return ResultView.success(ok);
    }
}
