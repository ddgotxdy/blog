package top.ddgotxdy.common.annotation;

import java.lang.annotation.*;

/**
 * @author: ddgo
 * @description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveWordProperty {
}
