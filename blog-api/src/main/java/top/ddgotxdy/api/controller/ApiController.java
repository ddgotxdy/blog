package top.ddgotxdy.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description:
 */
@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/getHello")
    public String getHello() {
        return "hello";
    }
}
