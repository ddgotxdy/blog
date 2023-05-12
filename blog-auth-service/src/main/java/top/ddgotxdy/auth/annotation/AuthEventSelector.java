package top.ddgotxdy.auth.annotation;

import top.ddgotxdy.auth.model.AuthEvent;

import java.lang.annotation.*;

/**
 * @author: ddgo
 * @description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthEventSelector {
    AuthEvent value();
}
