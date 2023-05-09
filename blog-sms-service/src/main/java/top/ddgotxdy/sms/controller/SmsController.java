package top.ddgotxdy.sms.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.common.model.*;
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
import top.ddgotxdy.sms.service.SmsCmdBizService;
import top.ddgotxdy.sms.service.SmsQueryBizService;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description:
 */
@RestController
@RequestMapping("openfeign/sms")
public class SmsController {
    @Resource
    private SmsCmdBizService smsCmdBizService;
    @Resource
    private SmsQueryBizService smsQueryBizService;

    @PostMapping("sensitive/add")
    public ResultView<IdDTO> addSensitive(
            @RequestBody SensitiveAddParam sensitiveAddParam
    ) {
        IdDTO idDTO = smsCmdBizService.addSensitive(sensitiveAddParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("sensitive/update")
    public ResultView<IdDTO> updateSensitive(
            @RequestBody SensitiveUpdateParam sensitiveUpdateParam
    ) {
        IdDTO idDTO = smsCmdBizService.updateSensitive(sensitiveUpdateParam);
        return ResultView.success(idDTO);
    }

    @DeleteMapping("sensitive/delete")
    public ResultView<IdsDTO> deleteSensitive(
            @RequestBody SensitiveDeleteParam sensitiveDeleteParam
    ) {
        IdsDTO idsDTO = smsCmdBizService.deleteSensitive(sensitiveDeleteParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("sensitive/recover")
    public ResultView<IdsDTO> recoverSensitive(
            @RequestBody SensitiveRecoverParam sensitiveRecoverParam
    ) {
        IdsDTO idsDTO = smsCmdBizService.recoverSensitive(sensitiveRecoverParam);
        return ResultView.success(idsDTO);
    }

    @PostMapping("sensitive/queryByPage")
    public ResultView<PageResult<SensitivePageListDTO>> querySensitiveByPage(
            @RequestBody PageQry<SensitiveQueryParam> sensitiveQueryParamPageQry
    ) {
        PageResult<SensitivePageListDTO> result = smsQueryBizService.querySensitiveByPage(sensitiveQueryParamPageQry);
        return ResultView.success(result);
    }

    @PostMapping("message/add")
    public ResultView<IdDTO> addMessage(
            @Validated @RequestBody MessageAddParam messageAddParam
    ) {
        IdDTO idDTO = smsCmdBizService.addMessage(messageAddParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("message/update")
    public ResultView<IdDTO> updateMessage(
            @RequestBody MessageUpdateParam messageUpdateParam
    ) {
        IdDTO idDTO = smsCmdBizService.updateMessage(messageUpdateParam);
        return ResultView.success(idDTO);
    }

    @PostMapping("message/queryByPage")
    public ResultView<PageResult<MessagePageListDTO>> queryMessageByPage(
            @RequestBody PageQry<MessageQueryParam> messageQueryParamPageQry
    ) {
        PageResult<MessagePageListDTO> result = smsQueryBizService.queryMessageByPage(messageQueryParamPageQry);
        return ResultView.success(result);
    }

}
