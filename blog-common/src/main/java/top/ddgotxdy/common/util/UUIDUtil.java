package top.ddgotxdy.common.util;

import java.util.UUID;

/**
 * @author: ddgo
 * @description: uuid工具类
 */
public class UUIDUtil {
    private UUIDUtil() { }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}