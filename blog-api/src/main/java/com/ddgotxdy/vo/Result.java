package com.ddgotxdy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ddgo
 * @description: 统一返回类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code;

    private String msg;

    private Object data;

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result success(String msg, Object data) {
        return new Result(200, msg, data);
    }

    public static Result fail(Integer code, String msg) {
        return new Result(code, msg, null);
    }
}