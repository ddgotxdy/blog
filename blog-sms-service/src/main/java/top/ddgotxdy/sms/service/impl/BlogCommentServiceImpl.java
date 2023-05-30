package top.ddgotxdy.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.dal.entity.BlogComment;
import top.ddgotxdy.dal.mapper.BlogCommentMapper;
import top.ddgotxdy.sms.service.BlogCommentService;

import java.util.List;

/**
 * @author ddgo
 * @description:
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    @Override
    public boolean deleteBatchByIds(List<Long> commentIds) {
        if (CollectionUtils.isEmpty(commentIds)) {
            return true;
        }
        LambdaUpdateWrapper<BlogComment> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogComment::getIsDelete, true)
                .in(BlogComment::getCommentId, commentIds);
        return this.update(updateWrapper);
    }

    @Override
    public boolean recoverBatchByIds(List<Long> commentIds) {
        if (CollectionUtils.isEmpty(commentIds)) {
            return true;
        }
        LambdaUpdateWrapper<BlogComment> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper
                .set(BlogComment::getIsDelete, false)
                .in(BlogComment::getCommentId, commentIds);
        return this.update(updateWrapper);
    }
}
