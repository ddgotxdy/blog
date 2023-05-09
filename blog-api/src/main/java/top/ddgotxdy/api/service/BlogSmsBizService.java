package top.ddgotxdy.api.service;

import top.ddgotxdy.api.model.addparam.SensitiveAddApiParam;
import top.ddgotxdy.api.model.queryparam.SensitiveQueryApiParam;
import top.ddgotxdy.api.model.updateparam.SensitiveUpdateApiParam;
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
}

