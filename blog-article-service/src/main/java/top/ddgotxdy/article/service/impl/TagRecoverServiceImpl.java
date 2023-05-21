package top.ddgotxdy.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ddgotxdy.article.annotation.ArticleEventSelector;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.article.model.ArticleEvent;
import top.ddgotxdy.article.service.AbstractArticleService;
import top.ddgotxdy.article.service.BlogTagService;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@ArticleEventSelector(ArticleEvent.TAG_RECOVER)
@Service
@Slf4j
public class TagRecoverServiceImpl extends AbstractArticleService {
    @Resource
    private BlogTagService blogTagService;

    @Override
    protected boolean filter(ArticleContext articleContext) {
        // 2. 标签列表是否传过来
        if (Objects.isNull(articleContext.getTagIds())) {
            throw new BlogException(ResultCode.TAG_RECOVERY_ERROR.getCode(), "标签列表为空");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void doExecute(ArticleContext articleContext) {
        List<Long> tagIds = articleContext.getTagIds();
        boolean isOk = blogTagService.recoverBatchByIds(tagIds);
        if (isOk) {
            articleContext.setTagIds(Collections.emptyList());
        }
    }
}
