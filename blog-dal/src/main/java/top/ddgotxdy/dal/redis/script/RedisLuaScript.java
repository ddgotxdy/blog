package top.ddgotxdy.dal.redis.script;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.List;

/**
 * @author: ddgo
 * @description: lua 脚本
 */
public class RedisLuaScript {
    private RedisLuaScript(){}
    public static final RedisScript<List> TEST = RedisScript.of(
            new ClassPathResource("lua/test.lua"), List.class);
}
