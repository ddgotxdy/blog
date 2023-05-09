package top.ddgotxdy.sms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogMessage;
import top.ddgotxdy.sms.annotation.SmsEventSelector;
import top.ddgotxdy.sms.convert.Context2EntityConvert;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.model.SmsEvent;
import top.ddgotxdy.sms.service.AbstractSmsService;
import top.ddgotxdy.sms.service.BlogMessageService;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@SmsEventSelector(SmsEvent.MESSAGE_UPDATE)
@Service
@Slf4j
public class MessageUpdateServiceImpl extends AbstractSmsService {
    @Resource
    private BlogMessageService blogMessageService;

    @Override
    protected boolean filter(SmsContext smsContext) {
        // 1. 必须传messageId
        if (Objects.isNull(smsContext.getMessageId())) {
            throw new BlogException(ResultCode.MESSAGE_UPDATE_ERROR.getCode(), "message id is null");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(SmsContext smsContext) {
        BlogMessage blogMessage = Context2EntityConvert.context2MessageForUpdate(smsContext);
        blogMessageService.updateById(blogMessage);
    }
}
