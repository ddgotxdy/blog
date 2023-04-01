package top.ddgotxdy.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ddgo
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("set")
    public void setRedis(@RequestParam(value = "key") String key,
                         @RequestParam(value = "value") String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("get")
    public String getRedis(@RequestParam(value = "key") String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

}
