package top.ddgotxdy.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.api.model.UserLoginApiModel;
import top.ddgotxdy.api.model.addparam.MenuAddApiParam;
import top.ddgotxdy.api.model.addparam.ResourceAddApiParam;
import top.ddgotxdy.api.model.addparam.RoleAddApiParam;
import top.ddgotxdy.api.model.addparam.UserAddApiParam;
import top.ddgotxdy.api.model.queryparam.MenuQueryApiParam;
import top.ddgotxdy.api.model.queryparam.ResourceQueryApiParam;
import top.ddgotxdy.api.model.queryparam.RoleQueryApiParam;
import top.ddgotxdy.api.model.queryparam.UserInfoQueryApiParam;
import top.ddgotxdy.api.model.updateparam.*;
import top.ddgotxdy.api.model.view.*;
import top.ddgotxdy.api.service.BlogAuthBizService;
import top.ddgotxdy.common.model.*;

import javax.annotation.Resource;
import java.util.List;

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

    @ApiOperation("更新用户的角色")
    @PostMapping("/admin/updateUserRole")
    public ResultView<IdView> updateUserRole(
            @Validated @RequestBody UserRoleUpdateApiParam userRoleUpdateApiParam
    ) {
        IdView idView = blogAuthBizService.updateUserRole(userRoleUpdateApiParam);
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

    @ApiOperation("根据id获取用户信息")
    @GetMapping("/user/getUserInfo/{userId}")
    public ResultView<UserInfoByIdView> getUserInfoById(
            @PathVariable("userId") Long userId
    ) {
        UserInfoByIdView userInfoByIdView = blogAuthBizService.getUserInfoById(userId);
        return ResultView.success(userInfoByIdView);
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

    @ApiOperation("删除用户")
    @DeleteMapping("/admin/deleteUser")
    ResultView<IdsView> deleteUser(
            @RequestBody List<Long> userIdList
    ) {
        IdsView idsView = blogAuthBizService.deleteUser(userIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("恢复用户")
    @PostMapping("/admin/recoverUser")
    ResultView<IdsView> recoverUser(
            @RequestBody List<Long> userIdList
    ) {
        IdsView idsView = blogAuthBizService.recoverUser(userIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("添加角色")
    @PostMapping("/admin/role/add")
    public ResultView<IdView> addRole(
            @Validated @RequestBody RoleAddApiParam roleAddApiParam
    ) {
        IdView idView = blogAuthBizService.addRole(roleAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("分页查询角色")
    @PostMapping("/admin/role/queryByPage")
    public ResultView<PageResult<RolePageListView>> queryRoleByPage(
            @Validated @RequestBody PageQry<RoleQueryApiParam> roleQueryApiParamPageQry
    ) {
        PageResult<RolePageListView> rolePageListViewPageResult = blogAuthBizService.queryRoleByPage(roleQueryApiParamPageQry);
        return ResultView.success(rolePageListViewPageResult);
    }

    @ApiOperation("修改角色")
    @PostMapping("/admin/role/update")
    public ResultView<IdView> updateRole(
            @Validated @RequestBody RoleUpdateApiParam roleUpdateApiParam
    ) {
        IdView idView = blogAuthBizService.updateRole(roleUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/admin/role/delete")
    public ResultView<IdsView> deleteRole(
            @RequestBody List<Long> roleIdList
    ) {
        IdsView idsView = blogAuthBizService.deleteRole(roleIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("恢复角色")
    @PostMapping("/admin/role/recover")
    public ResultView<IdsView> recoverRole(
            @RequestBody List<Long> roleIdList
    ) {
        IdsView idsView = blogAuthBizService.recoverRole(roleIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("查询单个角色名称")
    @GetMapping("/user/role/query/{roleId}")
    public ResultView<String> queryRoleById(
            @PathVariable("roleId") Long roleId
    ) {
        String roleName = blogAuthBizService.queryRoleById(roleId);
        return ResultView.success(roleName);
    }

    @ApiOperation("菜单添加接口")
    @PostMapping("/admin/menu/add")
    ResultView<IdView> addMenu(
            @Validated @RequestBody MenuAddApiParam menuAddApiParam
    ) {
        IdView idView = blogAuthBizService.addMenu(menuAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("菜单分页获取接口")
    @PostMapping("/admin/menu/queryByPage")
    ResultView<PageResult<MenuPageListView>> queryMenuByPage(
            @Validated @RequestBody PageQry<MenuQueryApiParam> menuQueryApiParamPageQry
    ) {
        PageResult<MenuPageListView> menuPageListViewPageResult
                = blogAuthBizService.queryMenuByPage(menuQueryApiParamPageQry);
        return ResultView.success(menuPageListViewPageResult);
    }

    @ApiOperation("菜单更新接口")
    @PostMapping("/admin/menu/update")
    ResultView<IdView> updateMenu(
            @Validated @RequestBody MenuUpdateApiParam menuUpdateApiParam
    ) {
        IdView idView = blogAuthBizService.updateMenu(menuUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("菜单删除接口")
    @DeleteMapping("/admin/menu/delete")
    ResultView<IdsView> deleteMenu(
            @RequestBody List<Long> menuIdList
    ) {
        IdsView idsView = blogAuthBizService.deleteMenu(menuIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("菜单恢复接口")
    @PostMapping("/admin/menu/recover")
    ResultView<IdsView> recoverMenu(
            @RequestBody List<Long> menuIdList
    ) {
        IdsView idsView = blogAuthBizService.recoverMenu(menuIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("资源添加接口")
    @PostMapping("/admin/resource/add")
    ResultView<IdView> addResource(
            @Validated @RequestBody ResourceAddApiParam resourceAddApiParam
    ) {
        IdView idView = blogAuthBizService.addResource(resourceAddApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("资源分页查询接口")
    @PostMapping("/admin/resource/queryByPage")
    ResultView<PageResult<ResourcePageListView>> queryResourceByPage(
            @Validated @RequestBody PageQry<ResourceQueryApiParam> resourceQueryApiParamPageQry
    ) {
        PageResult<ResourcePageListView> resourcePageListViewPageResult
                = blogAuthBizService.queryResourceByPage(resourceQueryApiParamPageQry);
        return ResultView.success(resourcePageListViewPageResult);
    }

    @ApiOperation("资源更新接口")
    @PostMapping("/admin/resource/update")
    ResultView<IdView> updateResource(
            @Validated @RequestBody ResourceUpdateApiParam resourceUpdateApiParam
    ) {
        IdView idView = blogAuthBizService.updateResource(resourceUpdateApiParam);
        return ResultView.success(idView);
    }

    @ApiOperation("资源删除接口")
    @DeleteMapping("/admin/resource/delete")
    ResultView<IdsView> deleteResource(
            @RequestBody List<Long> resourceIdList
    ) {
        IdsView idsView = blogAuthBizService.deleteResource(resourceIdList);
        return ResultView.success(idsView);
    }

    @ApiOperation("资源恢复接口")
    @PostMapping("/admin/resource/recover")
    ResultView<IdsView> recoverResource(
            @RequestBody List<Long> resourceIdList
    ) {
        IdsView idsView = blogAuthBizService.recoverResource(resourceIdList);
        return ResultView.success(idsView);
    }
}
