package top.ddgotxdy.sms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.sms.annotation.SmsEventSelector;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.model.SmsEvent;
import top.ddgotxdy.sms.service.AbstractSmsService;
import top.ddgotxdy.sms.service.BlogCommentService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@SmsEventSelector(SmsEvent.COMMENT_RECOVER)
@Service
@Slf4j
public class CommentRecoverServiceImpl extends AbstractSmsService {
    @Resource
    private BlogCommentService blogCommentService;

    @Override
    protected boolean filter(SmsContext smsContext) {
        // 1. user id 校验
        Long userId = smsContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.COMMENT_RECOVERY_ERROR.getCode(), "用户id为空");
        }
        // 2. comment ids 校验
        List<Long> commentIds = smsContext.getCommentIds();
        if (CollectionUtils.isEmpty(commentIds)) {
            throw new BlogException(ResultCode.COMMENT_RECOVERY_ERROR.getCode(), "评论ids为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(SmsContext smsContext) {
        List<Long> commentIds = smsContext.getCommentIds();
        boolean ok = blogCommentService.recoverBatchByIds(commentIds);
        if (ok) {
            smsContext.setCommentIds(Collections.emptyList());
        }
    }
}
