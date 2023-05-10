package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.convert.Context2EntityConvert;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogArticle;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static top.ddgotxdy.article.constant.ValidateConstant.MAX_ARTICLE_CONTENT_LENGTH;
import static top.ddgotxdy.article.constant.ValidateConstant.MAX_ARTICLE_TITLE_LENGTH;

/**
 * @author: ddgo
 * @description: 文章添加服务 为了区分article这个概念，加了body
 */
@ArticleEventSelector(ArticleEvent.ARTICLE_BODY_ADD)
@Service
@Slf4j
public class ArticleBodyAddServiceImpl extends AbstractArticleService {
    @Resource
    private BlogArticleService blogArticleService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkIsAdmin(articleContext);
        if (!allCommonCheck) {
            throw new BlogException(ResultCode.ARTICLE_ADD_ERROR.getCode(), "not admin");
        }
        // 2. 文章内容的大小不超过数据库的长度
        if (StringUtils.length(articleContext.getArticleContent()) > MAX_ARTICLE_CONTENT_LENGTH) {
            throw new BlogException(ResultCode.ARTICLE_ADD_ERROR.getCode(), "Over MAX_ARTICLE_CONTENT_LENGTH");
        }
        // 3. 必须包含一个标签
        List<Long> tagIds = articleContext.getTagIds();
        if (CollectionUtils.isEmpty(tagIds)) {
            throw new BlogException(ResultCode.ARTICLE_ADD_ERROR.getCode(), "tag ids is empty");
        }
        // 4. 必须包含一个分类id
        if (Objects.isNull(articleContext.getCategoryId())) {
            throw new BlogException(ResultCode.ARTICLE_ADD_ERROR.getCode(), "category id is null");
        }
        // 5. 标题长度校验
        String articleTitle = articleContext.getArticleTitle();
        if (StringUtils.length(articleTitle) > MAX_ARTICLE_TITLE_LENGTH
                || StringUtils.length(articleTitle) < 1) {
            throw new BlogException(ResultCode.ARTICLE_ADD_ERROR.getCode(), "Over MAX_ARTICLE_CONTENT_LENGTH or lower 1");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        // 1. 文章实体对象
        BlogArticle blogArticle = Context2EntityConvert.articleContext2ArticleForAdd(articleContext);
        // 2. 文章落库
        blogArticleService.save(blogArticle);
        // 3. 获取对应的文章主键id
        articleContext.setArticleId(blogArticle.getArticleId());
    }
}
