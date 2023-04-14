package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.convert.Context2EntityConvert;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.dal.entity.BlogTag;

import javax.annotation.Resource;
import java.util.Objects;

import static top.ddgotxdy.article.constant.ValidateConstant.MAX_TAG_LENGTH;

/**
 * @author: ddgo
 * @description: 标签更新服务类
 */
@ArticleEventSelector(ArticleEvent.TAG_UPDATE)
@Component
@Slf4j
public class TagUpdateServiceImpl extends AbstractArticleService {
    @Resource
    private BlogTagService blogTagService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkIsAdmin(articleContext)
                && this.checkUniqueTagName(articleContext);
        if (!allCommonCheck) {
            return false;
        }
        // 2. 传标签的大小不超过最长的长度
        if (Objects.nonNull(articleContext.getTagName())
                && (StringUtils.length(articleContext.getTagName()) > MAX_TAG_LENGTH
                || StringUtils.length(articleContext.getTagName()) < 1)) {
            // 先打error日志
            log.error("Over MAX_TAG_LENGTH or Lower 1");
            return false;
        }
        // 3. 标签id必须传递
        if (Objects.isNull(articleContext.getTagId())) {
            log.error("tag id is null");
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        BlogTag blogTag = Context2EntityConvert.articleContext2Tag(articleContext);
        blogTagService.updateById(blogTag);
    }
}
