package top.ddgotxdy.api.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description: 测试接口类
 */
@RestController
@RequestMapping("/user")
@Api(tags = "测试类")
public class TestController {

    @GetMapping("{name}")
    @ApiOperation("获取用户名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名字", required = true, paramType = "path")
    })
    public String getName(@PathVariable("name") String name) {
        return name;
    }
}
