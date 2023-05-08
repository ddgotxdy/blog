package top.ddgotxdy.sms.controller;

import org.springframework.web.bind.annotation.*;
import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdsDTO;
import top.ddgotxdy.common.model.ResultView;
import top.ddgotxdy.common.model.sms.addparam.SensitiveAddParam;
import top.ddgotxdy.common.model.sms.deleteparam.SensitiveDeleteParam;
import top.ddgotxdy.common.model.sms.recoverparam.SensitiveRecoverParam;
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

}
