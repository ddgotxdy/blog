package top.ddgotxdy.sms.convert;

import top.ddgotxdy.common.model.sms.addparam.SensitiveAddParam;
import top.ddgotxdy.common.model.sms.deleteparam.SensitiveDeleteParam;
import top.ddgotxdy.common.model.sms.recoverparam.SensitiveRecoverParam;
import top.ddgotxdy.common.model.sms.updateparam.SensitiveUpdateParam;
import top.ddgotxdy.common.util.BeanCopyUtil;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.model.SmsEvent;

/**
 * @author: ddgo
 * @description:
 */
public class Param2ContextConvert {
    private Param2ContextConvert() { }

    public static SmsContext addParamConvert(SensitiveAddParam sensitiveAddParam) {
        SmsContext smsContext = new SmsContext();
        // 先对无需处理的值复制一份
        BeanCopyUtil.copyProperties(sensitiveAddParam, smsContext);
        // 设置为添加事件
        smsContext.setSmsEvent(SmsEvent.SENSITIVE_ADD);
        return smsContext;
    }

    public static SmsContext updateParamConvert(SensitiveUpdateParam sensitiveUpdateParam) {
        SmsContext smsContext = new SmsContext();
        // 先对无需处理的值复制一份
        BeanCopyUtil.copyProperties(sensitiveUpdateParam, smsContext);
        // 设置为更新事件
        smsContext.setSmsEvent(SmsEvent.SENSITIVE_UPDATE);
        return smsContext;
    }

    public static SmsContext deleteParamContext(SensitiveDeleteParam sensitiveDeleteParam) {
        SmsContext smsContext = new SmsContext();
        // 先对无需处理的值复制一份
        BeanCopyUtil.copyProperties(sensitiveDeleteParam, smsContext);
        // 设置为删除事件
        smsContext.setSmsEvent(SmsEvent.SENSITIVE_DELETED);
        return smsContext;
    }

    public static SmsContext recoverSensitive(SensitiveRecoverParam sensitiveRecoverParam) {
        SmsContext smsContext = new SmsContext();
        // 先对无需处理的值复制一份
        BeanCopyUtil.copyProperties(sensitiveRecoverParam, smsContext);
        // 设置为恢复事件
        smsContext.setSmsEvent(SmsEvent.SENSITIVE_RECOVER);
        return smsContext;
    }
}
