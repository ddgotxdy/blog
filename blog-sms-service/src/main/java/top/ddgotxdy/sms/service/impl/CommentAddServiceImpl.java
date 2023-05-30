package top.ddgotxdy.sms.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogComment;
import top.ddgotxdy.sms.annotation.SmsEventSelector;
import top.ddgotxdy.sms.convert.Context2EntityConvert;
import top.ddgotxdy.sms.model.SmsContext;
import top.ddgotxdy.sms.model.SmsEvent;
import top.ddgotxdy.sms.service.AbstractSmsService;
import top.ddgotxdy.sms.service.BlogCommentService;

import javax.annotation.Resource;
import java.util.Objects;

import static top.ddgotxdy.sms.constant.ValidateConstant.MAX_COMMENT_LENGTH;
import static top.ddgotxdy.sms.constant.ValidateConstant.MIN_COMMENT_LENGTH;

/**
 * @author: ddgo
 * @description:
 */
@SmsEventSelector(SmsEvent.COMMENT_ADD)
@Service
@Slf4j
public class CommentAddServiceImpl extends AbstractSmsService {
    @Resource
    private BlogCommentService blogCommentService;

    @Override
    protected boolean filter(SmsContext smsContext) {
        // 1. user id 校验
        Long userId = smsContext.getUserId();
        if (Objects.isNull(userId)) {
            throw new BlogException(ResultCode.COMMENT_ADD_ERROR.getCode(), "用户id为空");
        }
        // 2. article id 校验
        Long articleId = smsContext.getArticleId();
        if (Objects.isNull(articleId)) {
            throw new BlogException(ResultCode.COMMENT_ADD_ERROR.getCode(), "文章id为空");
        }
        // 3. 评论校验
        String commentContent = smsContext.getCommentContent();
        if (StringUtils.length(commentContent) < MIN_COMMENT_LENGTH
                || StringUtils.length(commentContent) > MAX_COMMENT_LENGTH) {
            throw new BlogException(ResultCode.COMMENT_ADD_ERROR.getCode(), "评论回复长度错误");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(SmsContext smsContext) {
        BlogComment blogComment = Context2EntityConvert.context2CommentForAdd(smsContext);
        blogCommentService.save(blogComment);
        smsContext.setCommentId(blogComment.getCommentId());
    }
}
