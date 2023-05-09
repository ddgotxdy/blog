package top.ddgotxdy.sms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

import static top.ddgotxdy.sms.constant.ValidateConstant.MAX_MESSAGE_LENGTH;

/**
 * @author: ddgo
 * @description:
 */
@SmsEventSelector(SmsEvent.MESSAGE_ADD)
@Service
@Slf4j
public class MessageAddServiceImpl extends AbstractSmsService {
    @Resource
    private BlogMessageService blogMessageService;

    @Override
    protected boolean filter(SmsContext smsContext) {
        // 1. 长度校验
        String messageContent = smsContext.getMessageContent();
        if (StringUtils.length(messageContent) > MAX_MESSAGE_LENGTH
                || StringUtils.length(messageContent) < 1) {
            throw new BlogException(ResultCode.MESSAGE_ADD_ERROR.getCode(), "Over MAX_MESSAGE_LENGTH or lower 1");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(SmsContext smsContext) {
        BlogMessage blogMessage = Context2EntityConvert.context2MessageForAdd(smsContext);
        blogMessageService.save(blogMessage);
        smsContext.setMessageId(blogMessage.getMessageId());
    }
}
