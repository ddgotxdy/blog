package top.ddgotxdy.sms.service;

import top.ddgotxdy.common.model.IdDTO;
import top.ddgotxdy.common.model.IdsDTO;
import top.ddgotxdy.common.model.sms.addparam.CaptchaSendParam;
import top.ddgotxdy.common.model.sms.addparam.CommentAddParam;
import top.ddgotxdy.common.model.sms.addparam.MessageAddParam;
import top.ddgotxdy.common.model.sms.addparam.SensitiveAddParam;
import top.ddgotxdy.common.model.sms.deleteparam.CommentDeleteParam;
import top.ddgotxdy.common.model.sms.deleteparam.SensitiveDeleteParam;
import top.ddgotxdy.common.model.sms.recoverparam.CommentRecoverParam;
import top.ddgotxdy.common.model.sms.recoverparam.SensitiveRecoverParam;
import top.ddgotxdy.common.model.sms.updateparam.CommentUpdateParam;
import top.ddgotxdy.common.model.sms.updateparam.MessageUpdateParam;
import top.ddgotxdy.common.model.sms.updateparam.SensitiveUpdateParam;

/**
 * @author: ddgo
 * @description:
 */
public interface SmsCmdBizService {
    /**
     * 添加敏感词
     * @param sensitiveAddParam 敏感词添加参数
     * @return IdDTO
     */
    IdDTO addSensitive(SensitiveAddParam sensitiveAddParam);

    /**
     * 更新敏感词
     * @param sensitiveUpdateParam 敏感词更新参数
     * @return IdDTO
     */
    IdDTO updateSensitive(SensitiveUpdateParam sensitiveUpdateParam);

    /**
     * 删除敏感词
     * @param sensitiveDeleteParam 敏感词删除参数
     * @return IdsDTO
     */
    IdsDTO deleteSensitive(SensitiveDeleteParam sensitiveDeleteParam);

    /**
     * 恢复敏感词
     * @param sensitiveRecoverParam 敏感词恢复
     * @return IdsDTO
     */
    IdsDTO recoverSensitive(SensitiveRecoverParam sensitiveRecoverParam);

    /**
     * 留言添加
     * @param messageAddParam 留言添加参数
     * @return IdDTO
     */
    IdDTO addMessage(MessageAddParam messageAddParam);

    /**
     * 留言更新
     * @param messageUpdateParam
     * @return
     */
    IdDTO updateMessage(MessageUpdateParam messageUpdateParam);


    /**
     * 发生验证码
     * @param captchaSendParam 请求参数
     */
    void sendCaptcha(CaptchaSendParam captchaSendParam);

    IdDTO addComment(CommentAddParam commentAddParam);

    IdDTO updateComment(CommentUpdateParam commentUpdateParam);

    IdsDTO deleteComment(CommentDeleteParam commentDeleteParam);

    IdsDTO recoverComment(CommentRecoverParam commentRecoverParam);
}
