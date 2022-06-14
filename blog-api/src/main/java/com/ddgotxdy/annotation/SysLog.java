package com.ddgotxdy.annotation;

import java.lang.annotation.*;

/**
 * @author: ddgo
 * @description: 系统操作描述注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String module() default "";

    String operation() default "";
}
