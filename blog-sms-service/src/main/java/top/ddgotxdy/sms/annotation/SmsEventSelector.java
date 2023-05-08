package top.ddgotxdy.sms.annotation;

import top.ddgotxdy.sms.model.SmsEvent;

import java.lang.annotation.*;

/**
 * @author: ddgo
 * @description: 消息事件选择器
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SmsEventSelector {
    SmsEvent value();
}
