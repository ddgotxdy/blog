package top.ddgotxdy.sms.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdsDTO;
import top.ddgotxdy.common.model.sms.addparam.CaptchaSendParam;
import top.ddgotxdy.common.model.sms.addparam.CommentAddParam;
import top.ddgotxdy.common.model.sms.addparam.MessageAddParam;
import top.ddgotxdy.common.model.sms.addparam.SensitiveAddParam;
import top.ddgotxdy.common.model.sms.deleteparam.CommentDeleteParam;
import top.ddgotxdy.common.model.sms.deleteparam.SensitiveDeleteParam;
import top.ddgotxdy.common.model.sms.recoverparam.CommentRecoverParam;
import top.ddgotxdy.common.model.sms.recoverparam.SensitiveRecoverParam;
import top.ddgotxdy.common.model.sms.updateparam.CommentUpdateParam;
import top.ddgotxdy.common.model.sms.updateparam.MessageUpdateParam;
import top.ddgotxdy.common.model.sms.updateparam.SensitiveUpdateParam;
import top.ddgotxdy.common.util.RedisCache;
import top.ddgotxdy.sms.adaptor.SmsManageAdaptor;
import top.ddgotxdy.sms.convert.Param2ContextConvert;
import top.ddgotxdy.sms.model.EmailModel;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.service.EmailService;
import top.ddgotxdy.sms.service.SmsCmdBizService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static com.alibaba.fastjson.JSON.toJSON;
import static top.ddgotxdy.common.constant.RedisExpireTime.CAPTCHA_EXPIRE_SECOND;
import static top.ddgotxdy.common.constant.RedisPrefix.CAPTCHA;

/**
 * @author: ddgo
 * @description:
 */
@Service
@Slf4j
public class SmsCmdBizServiceImpl implements SmsCmdBizService {
    @Resource
    private SmsManageAdaptor smsManageAdaptor;
    @Resource
    private RedisCache redisCache;
    @Resource
    private EmailService emailService;

    @Override
    public IdDTO addSensitive(SensitiveAddParam sensitiveAddParam) {
        SmsContext smsContext = Param2ContextConvert.addParamConvert(sensitiveAddParam);
        log.info("SmsCmdBizServiceImpl addSensitive request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdDTO.builder()
                .id(smsContext.getSensitiveId())
                .build();
    }

    @Override
    public IdDTO updateSensitive(SensitiveUpdateParam sensitiveUpdateParam) {
        SmsContext smsContext = Param2ContextConvert.updateParamConvert(sensitiveUpdateParam);
        log.info("SmsCmdBizServiceImpl updateSensitive request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdDTO.builder()
                .id(smsContext.getSensitiveId())
                .build();
    }

    @Override
    public IdsDTO deleteSensitive(SensitiveDeleteParam sensitiveDeleteParam) {
        SmsContext smsContext = Param2ContextConvert.deleteParamContext(sensitiveDeleteParam);
        log.info("SmsCmdBizServiceImpl deleteSensitive request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdsDTO.builder()
                .ids(smsContext.getSensitiveIds())
                .build();
    }

    @Override
    public IdsDTO recoverSensitive(SensitiveRecoverParam sensitiveRecoverParam) {
        SmsContext smsContext = Param2ContextConvert.recoverSensitive(sensitiveRecoverParam);
        log.info("SmsCmdBizServiceImpl recoverSensitive request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdsDTO.builder()
                .ids(smsContext.getSensitiveIds())
                .build();
    }

    @Override
    public IdDTO addMessage(MessageAddParam messageAddParam) {
        SmsContext smsContext = Param2ContextConvert.addMessage(messageAddParam);
        log.info("SmsCmdBizServiceImpl addMessage request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdDTO.builder()
                .id(smsContext.getMessageId())
                .build();
    }

    @Override
    public IdDTO updateMessage(MessageUpdateParam messageUpdateParam) {
        SmsContext smsContext = Param2ContextConvert.updateMessage(messageUpdateParam);
        log.info("SmsCmdBizServiceImpl updateMessage request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdDTO.builder()
                .id(smsContext.getMessageId())
                .build();
    }

    @Override
    public void sendCaptcha(CaptchaSendParam captchaSendParam) {
        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-code.ftl");
        // 发送验证码
        EmailModel emailModel = new EmailModel();
        emailModel.setTos(Collections.singletonList(captchaSendParam.getMail()));
        emailModel.setSubject("邮箱验证码");
        String code = RandomUtil.randomNumbers(6);
        emailModel.setContent(template.render(Dict.create().set("code", code)));
        emailService.send(emailModel);
        // 将验证码设置到缓存里面
        redisCache.setCacheObject(
                CAPTCHA + captchaSendParam.getMail(),
                code,
                CAPTCHA_EXPIRE_SECOND,
                TimeUnit.SECONDS);
    }

    @Override
    public IdDTO addComment(CommentAddParam commentAddParam) {
        SmsContext smsContext = Param2ContextConvert.addParamConvert(commentAddParam);
        log.info("SmsCmdBizServiceImpl addComment request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdDTO.builder()
                .id(smsContext.getCommentId())
                .build();
    }

    @Override
    public IdDTO updateComment(CommentUpdateParam commentUpdateParam) {
        SmsContext smsContext = Param2ContextConvert.updateParamConvert(commentUpdateParam);
        log.info("SmsCmdBizServiceImpl updateComment request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdDTO.builder()
                .id(smsContext.getCommentId())
                .build();
    }

    @Override
    public IdsDTO deleteComment(CommentDeleteParam commentDeleteParam) {
        SmsContext smsContext = Param2ContextConvert.deleteParamContext(commentDeleteParam);
        log.info("SmsCmdBizServiceImpl deleteComment request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdsDTO.builder()
                .ids(smsContext.getCommentIds())
                .build();
    }

    @Override
    public IdsDTO recoverComment(CommentRecoverParam commentRecoverParam) {
        SmsContext smsContext = Param2ContextConvert.recoverSensitive(commentRecoverParam);
        log.info("SmsCmdBizServiceImpl recoverComment request[{}]", toJSON(smsContext));
        smsManageAdaptor.execute(smsContext);
        return IdsDTO.builder()
                .ids(smsContext.getCommentIds())
                .build();
    }
}
