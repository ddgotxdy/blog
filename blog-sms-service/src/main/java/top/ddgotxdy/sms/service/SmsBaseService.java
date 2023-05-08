package top.ddgotxdy.sms.service;

import top.ddgotxdy.sms.model.SmsContext;

/**
 * @author: ddgo
 * @description:
 */
public interface SmsBaseService {
    /**
     * 传入事件，根据事件进行事件的调用
     * @param smsContext 上下文
     */
    void execute(SmsContext smsContext);
}
