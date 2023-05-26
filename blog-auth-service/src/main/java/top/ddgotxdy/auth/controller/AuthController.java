package top.ddgotxdy.auth.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.auth.service.AuthCmdBizService;
import top.ddgotxdy.auth.service.AuthQueryBizService;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.auth.addparam.RoleAddParam;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.deleteparam.RoleDeleteParam;
import top.ddgotxdy.common.model.auth.deleteparam.UserRecoverParam;
import top.ddgotxdy.common.model.auth.dto.RolePageListDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoDTO;
import top.ddgotxdy.common.model.auth.dto.UserInfoPageListDTO;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;
import top.ddgotxdy.common.model.auth.queryparam.RoleQueryParam;
import top.ddgotxdy.common.model.auth.queryparam.UserInfoQueryParam;
import top.ddgotxdy.common.model.auth.recoverparam.RoleRecoverParam;
import top.ddgotxdy.common.model.auth.recoverparam.UserDeleteParam;
import top.ddgotxdy.common.model.auth.updateparam.*;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description:
 */
@RestController
@RequestMapping("openfeign/auth")
public class AuthController {

    @Resource
    private AuthQueryBizService authQueryBizService;
    @Resource
    private AuthCmdBizService authCmdBizService;

    @PostMapping("/register")
    public ResultView<IdDTO> register(
            @Validated @RequestBody UserAddParam userAddParam
    ) {
        IdDTO idDTO = authCmdBizService.register(userAddParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/login")
    public ResultView<String> login(
            @Validated @RequestBody UserLoginModel userLoginModel
    ) {
        String token = authCmdBizService.login(userLoginModel);
        return ResultView.success(token);
    }

    @PostMapping("/logout")
    public ResultView logout() {
        authCmdBizService.logout();
        return ResultView.success();
    }

    @GetMapping("/getUserInfo/{userId}")
    public ResultView<UserInfoDTO> getUserInfo(
            @PathVariable("userId") Long userId
    ) {
        UserInfoDTO userInfoDTO = authQueryBizService.getUserInfo(userId);
        return ResultView.success(userInfoDTO);
    }

    @PostMapping("/updatePassword")
    public ResultView<IdDTO> updatePassword(
            @Validated @RequestBody UserPasswordUpdateParam userPasswordUpdateParam
    ) {
        IdDTO idDTO = authCmdBizService.updatePassword(userPasswordUpdateParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/updateEmail")
    public ResultView<IdDTO> updateEmail(
            @Validated @RequestBody UserEmailUpdateParam userEmailUpdateParam
    ) {
        IdDTO idDTO = authCmdBizService.updateEmail(userEmailUpdateParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/updateUserInfo")
    public ResultView<IdDTO> updateUserInfo(
            @Validated @RequestBody UserInfoUpdateParam userInfoUpdateParam
    ) {
        IdDTO idDTO = authCmdBizService.updateUserInfo(userInfoUpdateParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/updateUserRole")
    public ResultView<IdDTO> updateUserRole(
            @Validated @RequestBody UserRoleUpdateParam userRoleUpdateParam
    ) {
        IdDTO idDTO = authCmdBizService.updateUserRole(userRoleUpdateParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/getUserInfoList")
    public ResultView<PageResult<UserInfoPageListDTO>> getUserInfoList(
            @Validated @RequestBody PageQry<UserInfoQueryParam> userInfoQueryParamPageQry
    ) {
        PageResult<UserInfoPageListDTO> userInfoDTOPageResult = authQueryBizService.getUserInfoList(userInfoQueryParamPageQry);
        return ResultView.success(userInfoDTOPageResult);
    }

    @DeleteMapping("/deleteUser")
    public ResultView<IdsDTO> deleteUser(
            @Validated @RequestBody UserDeleteParam userDeleteParam
    ) {
        IdsDTO idsDTO = authCmdBizService.deleteUser(userDeleteParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("/recoverUser")
    public ResultView<IdsDTO> recoverUser(
            @Validated @RequestBody UserRecoverParam userRecoverParam
    ) {
        IdsDTO idsDTO = authCmdBizService.recoverUser(userRecoverParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("/role/add")
    public ResultView<IdDTO> addRole(
            @Validated @RequestBody RoleAddParam roleAddParam
    ) {
        IdDTO idDTO = authCmdBizService.addRole(roleAddParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("/role/queryByPage")
    public ResultView<PageResult<RolePageListDTO>> queryRoleByPage(
            @Validated @RequestBody PageQry<RoleQueryParam> roleQueryParamPageQry
    ) {
        PageResult<RolePageListDTO> rolePageListDTOPageResult = authQueryBizService.queryRoleByPage(roleQueryParamPageQry);
        return ResultView.success(rolePageListDTOPageResult);
    }

    @PostMapping("/role/update")
    public ResultView<IdDTO> updateRole(
            @Validated @RequestBody RoleUpdateParam roleUpdateParam
    ) {
        IdDTO idDTO = authCmdBizService.updateRole(roleUpdateParam);
        return ResultView.success(idDTO);
    }

    @DeleteMapping("role/delete")
    public ResultView<IdsDTO> deleteRole(
            @Validated @RequestBody RoleDeleteParam roleDeleteParam
    ) {
        IdsDTO idsDTO = authCmdBizService.deleteRole(roleDeleteParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("role/recover")
    public ResultView<IdsDTO> recoverRole(
            @Validated @RequestBody RoleRecoverParam roleRecoverParam
    ) {
        IdsDTO idsDTO = authCmdBizService.recoverRole(roleRecoverParam);
        return ResultView.success(idsDTO);
    }
}
