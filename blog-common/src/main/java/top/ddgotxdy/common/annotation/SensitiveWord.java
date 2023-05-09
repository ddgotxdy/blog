package top.ddgotxdy.common.annotation;

import java.lang.annotation.*;

/**
 * @author: ddgo
 * @description: 敏感词过滤，标注某一个属性
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveWord {
}
