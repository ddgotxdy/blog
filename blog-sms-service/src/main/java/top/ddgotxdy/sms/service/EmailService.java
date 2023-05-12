package top.ddgotxdy.sms.service;

import top.ddgotxdy.sms.model.EmailModel;

/**
 * @author: ddgo
 * @description:
 */
public interface EmailService {

    /**
     * 发送邮件
     * @param emailModel 邮件对象
     */
    void send(EmailModel emailModel);
}
