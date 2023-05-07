package top.ddgotxdy.file.annotation;

import top.ddgotxdy.file.model.FileEvent;

import java.lang.annotation.*;

/**
 * @author: ddgo
 * @description: 文件事件分发注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FileEventSelector {
    FileEvent value();
}
