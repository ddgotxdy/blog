package com.ddgotxdy.handler;

import com.ddgotxdy.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ControllerAdvice对加了@Controller注解的方法进行拦截处理 AOP的实现
 * rest 表示返回json
 * @author: ddgo
 * @description: 全局异常拦截
 */
@RestControllerAdvice
public class AllExceptionHandler {
    /**
     * 进行异常处理，处理Exception.class的异常
     * @param ex 异常
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }

}
