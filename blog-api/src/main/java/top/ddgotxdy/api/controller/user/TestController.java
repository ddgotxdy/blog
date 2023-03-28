package top.ddgotxdy.api.controller.user;

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
public class TestController {
    @GetMapping("{name}")
    public String getName(@PathVariable("name") String name) {
        return name;
    }
}
