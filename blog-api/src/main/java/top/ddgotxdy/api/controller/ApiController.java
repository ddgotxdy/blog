package top.ddgotxdy.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description:
 */
@RestController("/api")
public class ApiController {
    @PostMapping("getHello")
    public String getHello() {
        return "hello";
    }
}
