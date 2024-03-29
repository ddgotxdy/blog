package top.ddgotxdy.sms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.enums.sms.SensitiveType;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogSensitive;
import top.ddgotxdy.sms.annotation.SmsEventSelector;
import top.ddgotxdy.sms.convert.Context2EntityConvert;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.model.SmsEvent;
import top.ddgotxdy.sms.service.AbstractSmsService;
import top.ddgotxdy.sms.service.BlogSensitiveService;

import javax.annotation.Resource;
import java.util.Objects;

import static top.ddgotxdy.sms.constant.ValidateConstant.MAX_WORD_LENGTH;

/**
 * @author: ddgo
 * @description:
 */
@SmsEventSelector(SmsEvent.SENSITIVE_UPDATE)
@Service
@Slf4j
public class SensitiveUpdateServiceImpl extends AbstractSmsService {
    @Resource
    private BlogSensitiveService blogSensitiveService;

    @Override
    protected boolean filter(SmsContext smsContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkUniqueSensitiveName(smsContext);
        if (!allCommonCheck) {
            throw new BlogException(ResultCode.SENSITIVE_UPDATE_ERROR.getCode(), "敏感词不唯一");
        }
        // 2. 敏感词长度<=50
        String word = smsContext.getWord();
        if (Objects.nonNull(word)
                && (StringUtils.length(word) > MAX_WORD_LENGTH || StringUtils.length(word) < 1)) {
            throw new BlogException(ResultCode.SENSITIVE_UPDATE_ERROR.getCode(), "敏感词长度错误");
        }
        // 3. 敏感词的类型是合法的
        Integer sensitiveType = smsContext.getSensitiveType();
        if (Objects.nonNull(sensitiveType)
                && SensitiveType.of(sensitiveType) == SensitiveType.SENSITIVE_UNKNOWN) {
            throw new BlogException(ResultCode.SENSITIVE_UPDATE_ERROR.getCode(), "敏感词类型错误");
        }
        // 4. 必须传主键
        Long sensitiveId = smsContext.getSensitiveId();
        if (Objects.isNull(sensitiveId)) {
            throw new BlogException(ResultCode.SENSITIVE_UPDATE_ERROR.getCode(), "敏感词id为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(SmsContext smsContext) {
        BlogSensitive blogSensitive = Context2EntityConvert.context2SensitiveForUpdate(smsContext);
        blogSensitiveService.updateById(blogSensitive);
    }
}
