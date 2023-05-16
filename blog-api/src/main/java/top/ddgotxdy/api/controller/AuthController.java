package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ddgotxdy.api.model.UserLoginApiModel;
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

    @PostMapping("/register")
    public ResultView<IdView> register(
            @Validated @RequestBody UserAddApiParam userAddApiParam
    ) {
        IdView idView = blogAuthBizService.register(userAddApiParam);
        return ResultView.success(idView);
    }

    @PostMapping("/login")
    public ResultView<String> login(
            @Validated @RequestBody UserLoginApiModel userLoginApiModel
    ) {
        String token = blogAuthBizService.login(userLoginApiModel);
        return ResultView.success(token);
    }

    @PostMapping("/logout")
    public ResultView logout() {
        blogAuthBizService.logout();
        return ResultView.success();
    }

    @PostMapping("/getUserInfo")
    public ResultView<UserInfoView> getUserInfo() {
        UserInfoView userInfoView = blogAuthBizService.getUserInfo();
        return ResultView.success(userInfoView);
    }
}
