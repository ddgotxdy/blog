package top.ddgotxdy.sms.service;

import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.sms.dto.CommentPageListDTO;
import top.ddgotxdy.common.model.sms.dto.MessagePageListDTO;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.model.sms.queryparam.CaptchaQueryParam;
import top.ddgotxdy.common.model.sms.queryparam.CommentQueryParam;
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

    /**
     * 获取验证码
     * @param captchaQueryParam
     * @return
     */
    String queryCaptcha(CaptchaQueryParam captchaQueryParam);

    PageResult<CommentPageListDTO> queryCommentByPage(PageQry<CommentQueryParam> commentQueryParamPageQry);

    PageResult<CommentPageListDTO> queryCommentTreeByPage(PageQry<CommentQueryParam> commentQueryParamPageQry);
}
