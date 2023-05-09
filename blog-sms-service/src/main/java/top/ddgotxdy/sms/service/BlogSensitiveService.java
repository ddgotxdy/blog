package top.ddgotxdy.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.ddgotxdy.dal.entity.BlogSensitive;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
public interface BlogSensitiveService extends IService<BlogSensitive> {

    /**
     * 批量删除敏感词
     * @param sensitiveIds 敏感词列表
     * @return boolean
     */
    boolean deleteBatchByIds(List<Long> sensitiveIds);

    /**
     * 批量恢复敏感词
     * @param sensitiveIds 敏感词列表
     * @return boolean
     */
    boolean recoverBatchByIds(List<Long> sensitiveIds);
}
