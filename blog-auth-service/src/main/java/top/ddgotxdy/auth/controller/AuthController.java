package top.ddgotxdy.auth.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ddgotxdy.auth.service.AuthCmdBizService;
import top.ddgotxdy.auth.service.AuthQueryBizService;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.auth.addparam.UserAddParam;
import top.ddgotxdy.common.model.auth.model.UserLoginModel;

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

}
