package top.ddgotxdy.article.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogArticleService;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.dal.entity.BlogArticle;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@ArticleEventSelector(ArticleEvent.TAG_DELETE)
@Service
@Slf4j
public class TagDeleteServiceImpl extends AbstractArticleService {
    @Resource
    private BlogTagService blogTagService;
    @Resource
    private BlogArticleService blogArticleService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 1. 所有通用校验逻辑全部校验通过
        boolean allCommonCheck = this.checkIsAdmin(articleContext);
        if (!allCommonCheck) {
            throw new BlogException(ResultCode.TAG_DELETE_ERROR.getCode(), "not admin");
        }
        // 2. 标签列表是否传过来
        if (Objects.isNull(articleContext.getTagIds())) {
            throw new BlogException(ResultCode.TAG_DELETE_ERROR.getCode(), "tag ids is null");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        // 剩下没被处理的tag Id
        List<Long> tagIdsRemain = new ArrayList<>();
        List<Long> tagIds = articleContext.getTagIds();
        tagIds.forEach(tagId -> {
            List<BlogArticle> blogArticleList = blogArticleService.getArticleByTagId(tagId);
            blogArticleList.forEach(blogArticle -> {
                List<Long> tagIdList = JSON.parseArray(blogArticle.getTagIds(), Long.class);
                if (CollectionUtils.isEmpty(tagIdList)) {
                    return;
                }
                // 没有被删除且引用这个标签的，不允许删除
                if (tagIdList.size() >= 1 && !blogArticle.getIsDelete()) {
                    tagIdsRemain.add(tagId);
                    return;
                }
                tagIdList.remove(tagId);
                blogArticle.setIsDelete(null);
                blogArticle.setTagIds(JSON.toJSONString(tagIdList));
                // 更新文章
                blogArticleService.updateById(blogArticle);
            });
            // 更新标签
            blogTagService.deleteById(tagId);
        });
        // 没有被删除
        articleContext.setTagIds(tagIdsRemain);
    }
}
