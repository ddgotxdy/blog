package top.ddgotxdy.sms.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.sms.model.EmailModel;
import top.ddgotxdy.sms.service.EmailService;

/**
 * @author: ddgo
 * @description:
 */
@Slf4j
@Service
@RefreshScope
public class EmailServiceImpl implements EmailService {
    @Value("${mail.email}")
    private String email;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.port}")
    private String port;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    @Override
    public void send(EmailModel emailModel) {
        // 读取邮箱配置
        if (email == null || host == null || port == null || username == null || password == null) {
            throw new RuntimeException("邮箱配置异常");
        }

        // 设置
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(Integer.parseInt(port));
        // 设置发送人邮箱
        account.setFrom(email);
        // 设置发送人名称
        account.setUser(username);
        // 设置发送授权码
        account.setPass(password);
        account.setAuth(true);
        // ssl方式发送
        account.setSslEnable(true);
        // 使用安全连接
        account.setStarttlsEnable(true);

        // 发送邮件
        try {
            int size = emailModel.getTos().size();
            Mail.create(account)
                    .setTos(emailModel.getTos().toArray(new String[size]))
                    .setTitle(emailModel.getSubject())
                    .setContent(emailModel.getContent())
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException(ResultCode.SEND_MAIL_ERROR.getCode(), ResultCode.SEND_MAIL_ERROR.getMsg());
        }
    }
}
