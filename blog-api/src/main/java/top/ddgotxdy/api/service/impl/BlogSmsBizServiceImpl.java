package top.ddgotxdy.api.service.impl;

import org.springframework.stereotype.Service;
import top.ddgotxdy.api.convert.SmsApiParam2ClientParamConvert;
import top.ddgotxdy.api.convert.SmsDTO2ViewConvert;
import top.ddgotxdy.api.model.addparam.CaptchaSendApiParam;
import top.ddgotxdy.api.model.addparam.MessageAddApiParam;
import top.ddgotxdy.api.model.addparam.SensitiveAddApiParam;
import top.ddgotxdy.api.model.queryparam.MessageQueryApiParam;
import top.ddgotxdy.api.model.queryparam.SensitiveQueryApiParam;
import top.ddgotxdy.api.model.updateparam.MessageUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.SensitiveUpdateApiParam;
import top.ddgotxdy.api.model.view.MessagePageListView;
import top.ddgotxdy.api.model.view.SensitivePageListView;
import top.ddgotxdy.api.service.BlogSmsBizService;
import top.ddgotxdy.common.client.BlogSmsClient;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.sms.addparam.CaptchaSendParam;
import top.ddgotxdy.common.model.sms.addparam.MessageAddParam;
import top.ddgotxdy.common.model.sms.addparam.SensitiveAddParam;
import top.ddgotxdy.common.model.sms.deleteparam.SensitiveDeleteParam;
import top.ddgotxdy.common.model.sms.dto.MessagePageListDTO;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.model.sms.queryparam.MessageQueryParam;
import top.ddgotxdy.common.model.sms.queryparam.SensitiveQueryParam;
import top.ddgotxdy.common.model.sms.recoverparam.SensitiveRecoverParam;
import top.ddgotxdy.common.model.sms.updateparam.MessageUpdateParam;
import top.ddgotxdy.common.model.sms.updateparam.SensitiveUpdateParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogSmsBizServiceImpl implements BlogSmsBizService {
    @Resource
    private BlogSmsClient blogSmsClient;

    @Override
    public IdView addSensitive(SensitiveAddApiParam sensitiveAddApiParam) {
        SensitiveAddParam sensitiveAddParam = SmsApiParam2ClientParamConvert.addApiParam2Param(sensitiveAddApiParam);
        ResultView<IdDTO> response = blogSmsClient.addSensitive(sensitiveAddParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdView updateSensitive(SensitiveUpdateApiParam sensitiveUpdateApiParam) {
        SensitiveUpdateParam sensitiveUpdateParam = SmsApiParam2ClientParamConvert.updateApiParam2Param(sensitiveUpdateApiParam);
        ResultView<IdDTO> response = blogSmsClient.updateSensitive(sensitiveUpdateParam);
        IdDTO idDTO = response.checkAndGetData();
        return IdView.builder()
                .id(idDTO.getId())
                .build();
    }

    @Override
    public IdsView deleteSensitive(List<Long> sensitiveIdList) {
        SensitiveDeleteParam sensitiveDeleteParam = SmsApiParam2ClientParamConvert.deleteApiParam2Param(sensitiveIdList);
        ResultView<IdsDTO> response = blogSmsClient.deleteSensitive(sensitiveDeleteParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public IdsView recoverSensitive(List<Long> sensitiveIdList) {
        SensitiveRecoverParam sensitiveRecoverParam = SmsApiParam2ClientParamConvert.recoveryApiParam2Param(sensitiveIdList);
        ResultView<IdsDTO> response = blogSmsClient.recoverSensitive(sensitiveRecoverParam);
        IdsDTO idsDTO = response.checkAndGetData();
        return IdsView.builder()
                .ids(idsDTO.getIds())
                .build();
    }

    @Override
    public PageResult<SensitivePageListView> querySensitiveByPage(PageQry<SensitiveQueryApiParam> sensitiveQueryApiParamPageQry) {
        PageQry<SensitiveQueryParam> sensitiveQueryParamPageQry = SmsApiParam2ClientParamConvert.sensitiveQueryApiParam2Param(sensitiveQueryApiParamPageQry);
        ResultView<PageResult<SensitivePageListDTO>> response = blogSmsClient.querySensitiveByPage(sensitiveQueryParamPageQry);
        PageResult<SensitivePageListView> sensitivePageListViewPageResult = SmsDTO2ViewConvert.sensitivePageListDTO2View(response.checkAndGetData());
        return sensitivePageListViewPageResult;
    }

    @Override
    public IdView addMessage(MessageAddApiParam messageAddApiParam) {
        MessageAddParam messageAddParam = SmsApiParam2ClientParamConvert.addApiParam2Param(messageAddApiParam);
        ResultView<IdDTO> response = blogSmsClient.addMessage(messageAddParam);
        IdDTO data = response.checkAndGetData();
        return IdView.builder()
                .id(data.getId())
                .build();
    }

    @Override
    public IdView updateMessage(MessageUpdateApiParam messageUpdateApiParam) {
        MessageUpdateParam messageUpdateParam = SmsApiParam2ClientParamConvert.updateApiParam2Param(messageUpdateApiParam);
        ResultView<IdDTO> response = blogSmsClient.updateMessage(messageUpdateParam);
        IdDTO data = response.checkAndGetData();
        return IdView.builder()
                .id(data.getId())
                .build();
    }

    @Override
    public PageResult<MessagePageListView> queryMessageByPage(PageQry<MessageQueryApiParam> messageQueryApiParamPageQry) {
        PageQry<MessageQueryParam> messageQueryParamPageQry = SmsApiParam2ClientParamConvert.messageQueryApiParam2Param(messageQueryApiParamPageQry);
        ResultView<PageResult<MessagePageListDTO>> response = blogSmsClient.queryMessageByPage(messageQueryParamPageQry);
        PageResult<MessagePageListView> sensitivePageListViewPageResult = SmsDTO2ViewConvert.messagePageListDTO2View(response.checkAndGetData());
        return sensitivePageListViewPageResult;
    }

    @Override
    public void sendCaptcha(CaptchaSendApiParam captchaSendApiParam) {
        // 比较简单
        CaptchaSendParam captchaSendParam = new CaptchaSendParam();
        captchaSendParam.setMail(captchaSendApiParam.getMail());
        blogSmsClient.sendCaptcha(captchaSendParam);
    }
}
