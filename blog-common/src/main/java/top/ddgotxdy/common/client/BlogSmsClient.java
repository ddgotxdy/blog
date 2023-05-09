package top.ddgotxdy.common.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.ddgotxdy.common.model.*;
import top.ddgotxdy.common.model.sms.addparam.SensitiveAddParam;
import top.ddgotxdy.common.model.sms.deleteparam.SensitiveDeleteParam;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.model.sms.queryparam.SensitiveQueryParam;
import top.ddgotxdy.common.model.sms.recoverparam.SensitiveRecoverParam;
import top.ddgotxdy.common.model.sms.updateparam.SensitiveUpdateParam;

/**
 * @author: ddgo
 * @description: 消息服务接口
 */
@Component
@FeignClient("sms-service")
public interface BlogSmsClient {
    /**
     * 敏感词添加
     * @param sensitiveAddParam 添加敏感词接口
     * @return ResultView<IdDTO>
     */
    @PostMapping("openfeign/sms/sensitive/add")
    ResultView<IdDTO> addSensitive(
            @RequestBody SensitiveAddParam sensitiveAddParam
    );

    /**
     * 敏感词更新
     * @param sensitiveUpdateParam 更新参数
     * @return ResultView<IdDTO>
     */
    @PostMapping("openfeign/sms/sensitive/update")
    ResultView<IdDTO> updateSensitive(
            @RequestBody SensitiveUpdateParam sensitiveUpdateParam
    );

    /**
     * 敏感词删除
     * @param sensitiveDeleteParam 删除参数
     * @return ResultView<IdsDTO>
     */
    @DeleteMapping("openfeign/sms/sensitive/delete")
    ResultView<IdsDTO> deleteSensitive(
            @RequestBody SensitiveDeleteParam sensitiveDeleteParam
    );

    /**
     * 敏感词恢复
     * @param sensitiveRecoverParam 恢复参数
     * @return ResultView<IdsDTO>
     */
    @PostMapping("openfeign/sms/sensitive/recover")
    ResultView<IdsDTO> recoverSensitive(
            @RequestBody SensitiveRecoverParam sensitiveRecoverParam
    );

    /**
     * 敏感词分页查询
     * @param sensitiveQueryParamPageQry 请求参数
     * @return ResultView<PageResult<SensitivePageListDTO>>
     */
    @PostMapping("openfeign/sms/sensitive/queryByPage")
    ResultView<PageResult<SensitivePageListDTO>> querySensitiveByPage(
            @RequestBody PageQry<SensitiveQueryParam> sensitiveQueryParamPageQry
    );
}