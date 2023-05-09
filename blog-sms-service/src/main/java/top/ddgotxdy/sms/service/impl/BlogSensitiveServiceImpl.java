package top.ddgotxdy.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.ddgotxdy.dal.entity.BlogSensitive;
import top.ddgotxdy.dal.mapper.BlogSensitiveMapper;
import top.ddgotxdy.sms.service.BlogSensitiveService;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class BlogSensitiveServiceImpl extends ServiceImpl<BlogSensitiveMapper, BlogSensitive> implements BlogSensitiveService {

    @Override
    public boolean deleteBatchByIds(List<Long> sensitiveIds) {
        LambdaUpdateWrapper<BlogSensitive> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogSensitive::getIsDelete, true)
                .in(BlogSensitive::getSensitiveId, sensitiveIds);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverBatchByIds(List<Long> sensitiveIds) {
        LambdaUpdateWrapper<BlogSensitive> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogSensitive::getIsDelete, false)
                .in(BlogSensitive::getSensitiveId, sensitiveIds);
        return this.update(updateWrapper);
    }

}
