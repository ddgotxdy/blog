package top.ddgotxdy.sms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdsDTO;
import top.ddgotxdy.common.model.sms.addparam.SensitiveAddParam;
import top.ddgotxdy.common.model.sms.deleteparam.SensitiveDeleteParam;
import top.ddgotxdy.common.model.sms.recoverparam.SensitiveRecoverParam;
import top.ddgotxdy.common.model.sms.updateparam.SensitiveUpdateParam;
import top.ddgotxdy.sms.adaptor.SmsManageAdaptor;
import top.ddgotxdy.sms.convert.Param2ContextConvert;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.service.SmsCmdBizService;

import javax.annotation.Resource;

import static com.alibaba.fastjson.JSON.toJSON;

/**
 * @author: ddgo
 * @description:
 */
@Service
@Slf4j
public class SmsCmdBizServiceImpl implements SmsCmdBizService {
    @Resource
    private SmsManageAdaptor smsManageAdaptor;

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
}
