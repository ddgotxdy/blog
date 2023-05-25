package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.addparam.CaptchaSendApiParam;
import top.ddgotxdy.api.model.addparam.MessageAddApiParam;
import top.ddgotxdy.api.model.addparam.SensitiveAddApiParam;
import top.ddgotxdy.api.model.queryparam.MessageQueryApiParam;
import top.ddgotxdy.api.model.queryparam.MessageQueryApiUserParam;
import top.ddgotxdy.api.model.queryparam.SensitiveQueryApiParam;
import top.ddgotxdy.api.model.updateparam.MessageUpdateApiParam;
import top.ddgotxdy.api.model.updateparam.SensitiveUpdateApiParam;
import top.ddgotxdy.api.model.view.MessagePageListUserView;
import top.ddgotxdy.api.model.view.MessagePageListView;
import top.ddgotxdy.api.model.view.SensitivePageListView;
import top.ddgotxdy.common.model.IdView;
import top.ddgotxdy.common.model.IdsView;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;

import java.util.List;

/**
 * @author: ddgo
 * @description: 消息接口
 */
public interface BlogSmsBizService {
    /**
     * 添加敏感词
     * @param sensitiveAddApiParam
     * @return
     */
    IdView addSensitive(SensitiveAddApiParam sensitiveAddApiParam);

    /**
     * 更新敏感词
     * @param sensitiveUpdateApiParam
     * @return
     */
    IdView updateSensitive(SensitiveUpdateApiParam sensitiveUpdateApiParam);

    /**
     * 删除敏感词
     * @param sensitiveIdList
     * @return
     */
    IdsView deleteSensitive(List<Long> sensitiveIdList);

    /**
     * 恢复敏感词
     * @param sensitiveIdList
     * @return
     */
    IdsView recoverSensitive(List<Long> sensitiveIdList);

    /**
     * 分页查询敏感词
     * @param sensitiveQueryApiParamPageQry
     * @return
     */
    PageResult<SensitivePageListView> querySensitiveByPage(PageQry<SensitiveQueryApiParam> sensitiveQueryApiParamPageQry);

    /**
     * 添加留言
     * @param messageAddApiParam
     * @return
     */
    IdView addMessage(MessageAddApiParam messageAddApiParam);

    /**
     * 更新留言
     * @param messageUpdateApiParam
     * @return
     */
    IdView updateMessage(MessageUpdateApiParam messageUpdateApiParam);

    /**
     * 分页查询留言
     * @param messageQueryApiParamPageQry
     * @return
     */
    PageResult<MessagePageListView> queryMessageByPage(PageQry<MessageQueryApiParam> messageQueryApiParamPageQry);

    /**
     * 发送验证码
     * @param captchaSendApiParam 验证码发送参数
     */
    void sendCaptcha(CaptchaSendApiParam captchaSendApiParam);

    /**
     * 用户分页请求留言
     * @param messageQueryApiUserParamPageQry 分页请求参数
     * @return PageResult<MessagePageListUserView>
     */
    PageResult<MessagePageListUserView> queryMessageByPageUser(PageQry<MessageQueryApiUserParam> messageQueryApiUserParamPageQry);
}

