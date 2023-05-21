package top.ddgotxdy.sms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.sms.annotation.SmsEventSelector;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.model.SmsEvent;
import top.ddgotxdy.sms.service.AbstractSmsService;
import top.ddgotxdy.sms.service.BlogSensitiveService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@SmsEventSelector(SmsEvent.SENSITIVE_DELETED)
@Service
@Slf4j
public class SensitiveDeleteServiceImpl extends AbstractSmsService {
    @Resource
    private BlogSensitiveService blogSensitiveService;

    @Override
    protected boolean filter(SmsContext smsContext) {
        // 2. 传递的列表
        if (Objects.isNull(smsContext.getSensitiveIds())) {
            throw new BlogException(ResultCode.SENSITIVE_DELETE_ERROR.getCode(), "敏感词id列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(SmsContext smsContext) {
        List<Long> sensitiveIds = smsContext.getSensitiveIds();
        boolean deleteOk = blogSensitiveService.deleteBatchByIds(sensitiveIds);
        // 没有被删除的
        if (deleteOk) {
            smsContext.setSensitiveIds(Collections.emptyList());
        }
    }
}
