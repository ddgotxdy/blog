package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.UserEmailUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserInfoUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserPasswordUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.UserRoleUpdateApiParam;
import top.ddgotxdy.api.model.view.UserInfoPageListView;
import top.ddgotxdy.api.model.view.UserInfoView;
import top.ddgotxdy.api.service.BlogAuthBizService;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
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

    @ApiOperation("更新密码")
    @PostMapping("/updatePassword")
    public ResultView<IdView> updatePassword(
            @Validated @RequestBody UserPasswordUpdateApiParam userPasswordUpdateApiParam
    ) {
        IdView idView = blogAuthBizService.updatePassword(userPasswordUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("更新邮箱")
    @PostMapping("/updateEmail")
    public ResultView<IdView> updateEmail(
            @Validated @RequestBody UserEmailUpdateApiParam userEmailUpdateApiParam
    ) {
        IdView idView = blogAuthBizService.updateEmail(userEmailUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/updateUserInfo")
    public ResultView<IdView> updateUserInfo(
            @Validated @RequestBody UserInfoUpdateApiParam userInfoUpdateApiParam
    ) {
        IdView idView = blogAuthBizService.updateUserInfo(userInfoUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("更新角色")
    @PostMapping("/admin/updateRole")
    public ResultView<IdView> updateRole(
            @Validated @RequestBody UserRoleUpdateApiParam userRoleUpdateApiParam
    ) {
        IdView idView = blogAuthBizService.updateRole(userRoleUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("获取用户列表信息")
    @PostMapping("/admin/getUserInfoList")
    public ResultView<PageResult<UserInfoPageListView>> getUserInfoList(
            @Validated @RequestBody PageQry<UserInfoQueryApiParam> userInfoQueryApiParamPageQry
    ) {
        PageResult<UserInfoPageListView> userInfoViewPageResult = blogAuthBizService.getUserInfoList(userInfoQueryApiParamPageQry);
        return ResultView.success(userInfoViewPageResult);
    }

    @ApiOperation("判断用户名是否合法")
    @GetMapping("/checkUsername")
    public ResultView<Boolean> checkUsername(
            @RequestParam("username") String username
    ) {
        Boolean ok = blogAuthBizService.checkUsername(username);
        return ResultView.success(ok);
    }

    @ApiOperation("判断邮箱是否合法")
    @GetMapping("/checkEmail")
    public ResultView<Boolean> checkEmail(
            @RequestParam("email") String email
    ) {
        Boolean ok = blogAuthBizService.checkEmail(email);
        return ResultView.success(ok);
    }
}
