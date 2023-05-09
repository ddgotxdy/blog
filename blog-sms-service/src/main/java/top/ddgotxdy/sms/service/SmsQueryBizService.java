package top.ddgotxdy.sms.service;

import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.sms.dto.MessagePageListDTO;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.model.sms.queryparam.MessageQueryParam;
import top.ddgotxdy.common.model.sms.queryparam.SensitiveQueryParam;

/**
 * @author: ddgo
 * @description:
 */
public interface SmsQueryBizService {
    /**
     * 分页查询敏感词
     * @param sensitiveQueryParamPageQry 请求参数
     * @return PageResult<SensitivePageListDTO>
     */
    PageResult<SensitivePageListDTO> querySensitiveByPage(PageQry<SensitiveQueryParam> sensitiveQueryParamPageQry);

    /**
     * 分页查询留言
     * @param messageQueryParamPageQry
     * @return
     */
    PageResult<MessagePageListDTO> queryMessageByPage(PageQry<MessageQueryParam> messageQueryParamPageQry);
}
