package top.ddgotxdy.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.PageQry;
import top.ddgotxdy.common.model.PageResult;
import top.ddgotxdy.common.model.sms.dto.CommentPageListDTO;
import top.ddgotxdy.common.model.sms.dto.MessagePageListDTO;
import top.ddgotxdy.common.model.sms.dto.SensitivePageListDTO;
import top.ddgotxdy.common.model.sms.queryparam.CaptchaQueryParam;
import top.ddgotxdy.common.model.sms.queryparam.CommentQueryParam;
import top.ddgotxdy.common.model.sms.queryparam.MessageQueryParam;
import top.ddgotxdy.common.model.sms.queryparam.SensitiveQueryParam;
import top.ddgotxdy.common.util.RedisCache;
import top.ddgotxdy.dal.entity.BlogComment;
import top.ddgotxdy.dal.entity.BlogMessage;
import top.ddgotxdy.dal.entity.BlogSensitive;
import top.ddgotxdy.sms.convert.Entity2DTOConvert;
import top.ddgotxdy.sms.convert.FieldName2FunctionConvert;
import top.ddgotxdy.sms.service.BlogCommentService;
import top.ddgotxdy.sms.service.BlogMessageService;
import top.ddgotxdy.sms.service.BlogSensitiveService;
import top.ddgotxdy.sms.service.SmsQueryBizService;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static top.ddgotxdy.common.constant.RedisPrefix.CAPTCHA;
import static top.ddgotxdy.common.constant.WhiteListConstant.CAPTCHA_WHITE;
import static top.ddgotxdy.common.constant.WhiteListConstant.EMAIL_WHITE;

/**
 * @author: ddgo
 * @description:
 */
@Service
public class SmsQueryBizServiceImpl implements SmsQueryBizService {
    @Resource
    private BlogSensitiveService blogSensitiveService;
    @Resource
    private BlogMessageService blogMessageService;
    @Resource
    private RedisCache redisCache;
    @Resource
    private BlogCommentService blogCommentService;

    @Override
    public PageResult<SensitivePageListDTO> querySensitiveByPage(PageQry<SensitiveQueryParam> sensitiveQueryParamPageQry) {
        // 分页参数组装
        int pageNum = sensitiveQueryParamPageQry.getPageNum();
        int pageSize = sensitiveQueryParamPageQry.getPageSize();
        Page<BlogSensitive> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogSensitive> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        SensitiveQueryParam queryParam = sensitiveQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getSensitiveId()), BlogSensitive::getSensitiveId, queryParam.getSensitiveId())
                .eq(Objects.nonNull(queryParam.getIsDelete()), BlogSensitive::getIsDelete, queryParam.getIsDelete())
                .eq(Objects.nonNull(queryParam.getSensitiveType()), BlogSensitive::getSensitiveType, queryParam.getSensitiveType())
                .like(Objects.nonNull(queryParam.getWord()), BlogSensitive::getWord, queryParam.getWord());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = sensitiveQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.sensitiveFiledName2Function(name))
        );
        Page<BlogSensitive> blogSensitivePage = blogSensitiveService.page(page, queryWrapper);
        List<BlogSensitive> blogSensitiveList = blogSensitivePage.getRecords();
        List<SensitivePageListDTO> sensitivePageListDTOList = Entity2DTOConvert.sensitiveList2DTO(blogSensitiveList);
        // 封装返回值
        PageResult<SensitivePageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogSensitivePage.getPages());
        pageResult.setData(sensitivePageListDTOList);
        return pageResult;
    }

    @Override
    public PageResult<MessagePageListDTO> queryMessageByPage(PageQry<MessageQueryParam> messageQueryParamPageQry) {
        // 分页参数组装
        int pageNum = messageQueryParamPageQry.getPageNum();
        int pageSize = messageQueryParamPageQry.getPageSize();
        Page<BlogMessage> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogMessage> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        MessageQueryParam queryParam = messageQueryParamPageQry.getQueryParam();
        queryWrapper
                .eq(Objects.nonNull(queryParam.getMessageId()), BlogMessage::getMessageId, queryParam.getMessageId())
                .eq(Objects.nonNull(queryParam.getAuditType()), BlogMessage::getAuditType, queryParam.getAuditType())
                .like(Objects.nonNull(queryParam.getMessageContent()), BlogMessage::getMessageContent, queryParam.getMessageContent());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = messageQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.messageFiledName2Function(name))
        );
        Page<BlogMessage> blogMessagePage = blogMessageService.page(page, queryWrapper);
        List<BlogMessage> blogMessageList = blogMessagePage.getRecords();
        List<MessagePageListDTO> messagePageListDTOList = Entity2DTOConvert.messageList2DTO(blogMessageList);
        // 封装返回值
        PageResult<MessagePageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogMessagePage.getPages());
        pageResult.setData(messagePageListDTOList);
        return pageResult;
    }

    @Override
    public String queryCaptcha(CaptchaQueryParam captchaQueryParam) {
        // 测试邮箱返回对应得验证码
        String mail = captchaQueryParam.getMail();
        if (Objects.equals(EMAIL_WHITE, mail)) {
            return CAPTCHA_WHITE;
        }
        String key = CAPTCHA + mail;
        if (!redisCache.hasKey(key)) {
            throw new BlogException(ResultCode.CAPTCHA_EXPIRE_ERROR);
        }
        String captcha = redisCache.getCacheObject(CAPTCHA + mail);
        return captcha;
    }

    @Override
    public PageResult<CommentPageListDTO> queryCommentByPage(
            PageQry<CommentQueryParam> commentQueryParamPageQry
    ) {
        return null;
    }

    @Override
    public PageResult<CommentPageListDTO> queryCommentTreeByPage(
            PageQry<CommentQueryParam> commentQueryParamPageQry
    ) {
        // 分页参数组装【先获取一级评论】
        int pageNum = commentQueryParamPageQry.getPageNum();
        int pageSize = commentQueryParamPageQry.getPageSize();
        Page<BlogComment> page = new Page<>(pageNum, pageSize);
        // 查询参数组装
        LambdaQueryWrapper<BlogComment> queryWrapper = new LambdaQueryWrapper<>();
        // 查询值
        CommentQueryParam queryParam = commentQueryParamPageQry.getQueryParam();
        queryWrapper
                .isNull(BlogComment::getParentId)
                .eq(Objects.nonNull(queryParam.getArticleId()), BlogComment::getArticleId, queryParam.getArticleId())
                .eq(Objects.nonNull(queryParam.getAuditType()), BlogComment::getAuditType, queryParam.getAuditType())
                .eq(Objects.nonNull(queryParam.getCommentId()), BlogComment::getCommentId, queryParam.getCommentId())
                .eq(Objects.nonNull(queryParam.getUserId()), BlogComment::getUserId, queryParam.getUserId())
                .like(Objects.nonNull(queryParam.getCommentContent()), BlogComment::getCommentContent, queryParam.getCommentContent());
        // 排序规则
        LinkedHashMap<String, Boolean> orderByFields = commentQueryParamPageQry.getOrderByFields();
        if (CollectionUtils.isEmpty(orderByFields)) {
            orderByFields = new LinkedHashMap<>();
            orderByFields.put("createTime", false);
        }
        orderByFields.forEach((name, asc) ->
                queryWrapper.orderBy(true, asc, FieldName2FunctionConvert.commentFiledName2Function(name))
        );
        Page<BlogComment> blogCommentPage = blogCommentService.page(page, queryWrapper);
        List<BlogComment> blogCommentList = blogCommentPage.getRecords();
        List<CommentPageListDTO> commentPageListDTOList = Entity2DTOConvert.commentList2TreeDTO(blogCommentList);
        // 封装返回值
        PageResult<CommentPageListDTO> pageResult = new PageResult<>();
        pageResult.setTotalPage(blogCommentPage.getPages());
        pageResult.setData(commentPageListDTOList);
        return pageResult;
    }
}
