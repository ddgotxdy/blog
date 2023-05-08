package top.ddgotxdy.sms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.sms.annotation.SmsEventSelector;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.model.SmsEvent;
import top.ddgotxdy.sms.service.AbstractSmsService;

/**
 * @author: ddgo
 * @description:
 */
@SmsEventSelector(SmsEvent.SENSITIVE_UPDATE)
@Service
@Slf4j
public class SensitiveUpdateServiceImpl extends AbstractSmsService {
    @Override
    protected boolean filter(SmsContext smsContext) {
        return false;
    }

    @Override
    protected void doExecute(SmsContext smsContext) {

    }
}
