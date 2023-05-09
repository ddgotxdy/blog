package top.ddgotxdy.sms.service.impl;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
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
@SmsEventSelector(SmsEvent.SENSITIVE_ADD)
@Service
@Slf4j
public class SensitiveAddServiceImpl extends AbstractSmsService {
    @Resource
    private BlogSensitiveService blogSensitiveService;

    @Override
    protected boolean filter(SmsContext smsContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkIsAdmin(smsContext)
                && this.checkUniqueSensitiveName(smsContext);
        if (!allCommonCheck) {
            throw new BlogException(ResultCode.SENSITIVE_ADD_ERROR.getCode(), "not admin");
        }
        // 2. 敏感词长度<=50
        String word = smsContext.getWord();
        if (StringUtils.length(word) > MAX_WORD_LENGTH || StringUtils.length(word) < 1) {
            throw new BlogException(ResultCode.SENSITIVE_ADD_ERROR.getCode(), "Over MAX_WORD_LENGTH or lower 1");
        }
        // 3. 敏感词的类型是合法的
        Integer sensitiveType = smsContext.getSensitiveType();
        if (Objects.isNull(sensitiveType)
                || SensitiveType.of(sensitiveType) == SensitiveType.SENSITIVE_UNKNOWN) {
            throw new BlogException(ResultCode.SENSITIVE_ADD_ERROR.getCode(), "SensitiveType error");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(SmsContext smsContext) {
        BlogSensitive blogSensitive = Context2EntityConvert.context2SensitiveForAdd(smsContext);
        blogSensitiveService.save(blogSensitive);
        smsContext.setSensitiveId(blogSensitive.getSensitiveId());
    }
}
