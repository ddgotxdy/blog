package top.ddgotxdy.api.convert;

import top.ddgotxdy.api.model.addparam.MessageAddApiParam;
import top.ddgotxdy.api.model.addparam.SensitiveAddApiParam;
import top.ddgotxdy.api.model.queryparam.MessageQueryApiParam;
import top.ddgotxdy.api.model.queryparam.SensitiveQueryApiParam;
import top.ddgotxdy.api.model.updateparam.MessageUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.SensitiveUpdateApiParam;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.sms.addparam.MessageAddParam;
import top.ddgotxdy.common.model.sms.addparam.SensitiveAddParam;
import top.ddgotxdy.common.model.sms.deleteparam.SensitiveDeleteParam;
import top.ddgotxdy.common.model.sms.queryparam.MessageQueryParam;
import top.ddgotxdy.common.model.sms.queryparam.SensitiveQueryParam;
import top.ddgotxdy.common.model.sms.recoverparam.SensitiveRecoverParam;
import top.ddgotxdy.common.model.sms.updateparam.MessageUpdateParam;
import top.ddgotxdy.common.model.sms.updateparam.SensitiveUpdateParam;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.util.BeanCopyUtil;

import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
public class SmsApiParam2ClientParamConvert {
    private SmsApiParam2ClientParamConvert() { }

    public static SensitiveAddParam addApiParam2Param(SensitiveAddApiParam sensitiveAddApiParam) {
        SensitiveAddParam sensitiveAddParam = new SensitiveAddParam();
        BeanCopyUtil.copyProperties(sensitiveAddApiParam, sensitiveAddParam);
        // 枚举值处理
        sensitiveAddParam.setSensitiveType(sensitiveAddApiParam.getSensitiveType().getCode());
        Long userId = ContextScope.getUserId();
        sensitiveAddParam.setUserId(userId);
        return sensitiveAddParam;
    }

    public static SensitiveUpdateParam updateApiParam2Param(SensitiveUpdateApiParam sensitiveUpdateApiParam) {
        SensitiveUpdateParam sensitiveUpdateParam = new SensitiveUpdateParam();
        BeanCopyUtil.copyProperties(sensitiveUpdateApiParam, sensitiveUpdateParam);
        // 枚举值处理，存在则赋值
        if (Objects.nonNull(sensitiveUpdateApiParam.getSensitiveType())) {
            sensitiveUpdateParam.setSensitiveType(sensitiveUpdateApiParam.getSensitiveType().getCode());
        }
        Long userId = ContextScope.getUserId();
        sensitiveUpdateParam.setUserId(userId);
        return sensitiveUpdateParam;
    }

    public static SensitiveDeleteParam deleteApiParam2Param(List<Long> sensitiveIdList) {
        SensitiveDeleteParam sensitiveDeleteParam = new SensitiveDeleteParam();
        sensitiveDeleteParam.setSensitiveIds(sensitiveIdList);
        Long userId = ContextScope.getUserId();
        sensitiveDeleteParam.setUserId(userId);
        return sensitiveDeleteParam;
    }

    public static SensitiveRecoverParam recoveryApiParam2Param(List<Long> sensitiveIdList) {
        SensitiveRecoverParam sensitiveRecoverParam = new SensitiveRecoverParam();
        sensitiveRecoverParam.setSensitiveIds(sensitiveIdList);
        Long userId = ContextScope.getUserId();
        sensitiveRecoverParam.setUserId(userId);
        return sensitiveRecoverParam;
    }

    public static PageQry<SensitiveQueryParam> sensitiveQueryApiParam2Param(PageQry<SensitiveQueryApiParam> sensitiveQueryApiParamPageQry) {
        SensitiveQueryParam sensitiveQueryParam = new SensitiveQueryParam();
        // 范型复制
        SensitiveQueryApiParam sensitiveQueryApiParam = sensitiveQueryApiParamPageQry.getQueryParam();
        BeanCopyUtil.copyProperties(sensitiveQueryApiParam, sensitiveQueryParam);
        // 枚举值处理
        if (Objects.nonNull(sensitiveQueryApiParam.getSensitiveType())) {
            sensitiveQueryParam.setSensitiveType(sensitiveQueryApiParam.getSensitiveType().getCode());
        }
        // 分页参数复制
        PageQry<SensitiveQueryParam> sensitiveQueryParamPageQry = new PageQry<>();
        BeanCopyUtil.copyProperties(sensitiveQueryApiParamPageQry, sensitiveQueryParamPageQry);
        // 范型赋值进去
        sensitiveQueryParamPageQry.setQueryParam(sensitiveQueryParam);
        return sensitiveQueryParamPageQry;
    }

    public static MessageAddParam addApiParam2Param(MessageAddApiParam messageAddApiParam) {
        MessageAddParam messageAddParam = new MessageAddParam();
        BeanCopyUtil.copyProperties(messageAddApiParam, messageAddParam);
        return messageAddParam;
    }

    public static MessageUpdateParam updateApiParam2Param(MessageUpdateApiParam messageUpdateApiParam) {
        MessageUpdateParam messageUpdateParam = new MessageUpdateParam();
        BeanCopyUtil.copyProperties(messageUpdateApiParam, messageUpdateParam);
        // 枚举值处理
        if (Objects.nonNull(messageUpdateApiParam.getAuditType())) {
            messageUpdateParam.setAuditType(messageUpdateApiParam.getAuditType().getCode());
        }
        Long userId = ContextScope.getUserId();
        messageUpdateParam.setUserId(userId);
        return messageUpdateParam;
    }

    public static PageQry<MessageQueryParam> messageQueryApiParam2Param(PageQry<MessageQueryApiParam> messageQueryApiParamPageQry) {
        MessageQueryParam messageQueryParam = new MessageQueryParam();
        // 范型复制
        MessageQueryApiParam messageQueryApiParam = messageQueryApiParamPageQry.getQueryParam();
        BeanCopyUtil.copyProperties(messageQueryApiParam, messageQueryParam);
        // 枚举值处理
        if (Objects.nonNull(messageQueryApiParam.getAuditType())) {
            messageQueryParam.setAuditType(messageQueryApiParam.getAuditType().getCode());
        }
        // 分页参数复制
        PageQry<MessageQueryParam> messageQueryParamPageQry = new PageQry<>();
        BeanCopyUtil.copyProperties(messageQueryApiParamPageQry, messageQueryParamPageQry);
        // 范型赋值进去
        messageQueryParamPageQry.setQueryParam(messageQueryParam);
        return messageQueryParamPageQry;
    }
}
