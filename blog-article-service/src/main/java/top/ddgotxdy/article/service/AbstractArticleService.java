package top.ddgotxdy.article.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.common.enums.OplogStage;
import top.ddgotxdy.common.enums.OplogType;
import top.ddgotxdy.common.service.BlogOplogService;
import top.ddgotxdy.dal.entity.BlogCategory;
import top.ddgotxdy.dal.entity.BlogOplog;
import top.ddgotxdy.dal.entity.BlogTag;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author: ddgo
 * @description: 文章服务抽象类
 */
@Slf4j
public abstract class AbstractArticleService implements ArticleBaseService {
    @Resource
    private BlogCategoryService blogCategoryService;
    @Resource
    private BlogTagService blogTagService;
    @Resource
    private BlogOplogService blogOplogService;

    @Override
    public void execute(ArticleContext articleContext) {
        // 过滤条件不满足
        if (!filter(articleContext)) {
            return;
        }
        // 通过校验，操作日志预落库
        Long oplogId = preOplog(articleContext);
        // 开始执行文章操作
        doExecute(articleContext);
        // 已经落库，操作日志实际落库
        afterOplog(oplogId);
    }

    /**
     * 合法性校验
     * @param articleContext 文章上下文
     * @return 是否通过校验
     */
    protected abstract boolean filter(ArticleContext articleContext);

    /**
     * 开始执行操作
     * @param articleContext 文章上下文
     */
    protected abstract void doExecute(ArticleContext articleContext);

    /**
     * 操作日志预落库
     * @param articleContext 文章上下文
     */
    protected Long preOplog(ArticleContext articleContext) {
        BlogOplog blogOplog = new BlogOplog();
        blogOplog.setUserId(articleContext.getUserId());
        blogOplog.setOperatorType(
                OplogType.valueOf(articleContext.getArticleEvent().name()).getCode()
        );
        blogOplog.setOperatorContent(JSON.toJSONString(articleContext));
        blogOplog.setOperatorStage(OplogStage.PRE_OPLOG.getCode());
        blogOplogService.save(blogOplog);
        return blogOplog.getOperatorId();
    }

    /**
     * 操作日志后落库
     * @param oplogId 文章上下文
     */
    protected void afterOplog(Long oplogId) {
        BlogOplog blogOplog = new BlogOplog();
        blogOplog.setOperatorId(oplogId);
        blogOplog.setOperatorStage(OplogStage.AFTER_OPLOG.getCode());
    }

    /**
     * 校验分类名称是否唯一
     * @param articleContext 文章上下文
     * @return true 唯一，false 不唯一
     */
    protected boolean checkUniqueCategoryName(ArticleContext articleContext) {
        // 更新的分类具有唯一性，TODO 直接用mysql查，可能包含性能问题
        if (Objects.isNull(articleContext.getCategoryName())) {
            return true;
        }
        LambdaQueryWrapper<BlogCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(
                Objects.nonNull(articleContext.getCategoryName()),
                BlogCategory::getCategoryName,
                articleContext.getCategoryName()
        );
        List<BlogCategory> blogCategoryList = blogCategoryService.list(queryWrapper);
        if (!CollectionUtils.isEmpty(blogCategoryList)) {
            log.error("category name is exits");
            return false;
        }
        return true;
    }

    /**
     * 校验标签的唯一性
     * @param articleContext 文章上下文
     * @return true 唯一  false 不唯一
     */
    protected boolean checkUniqueTagName(ArticleContext articleContext) {
        // 更新的分类具有唯一性，TODO 直接用mysql查，可能包含性能问题
        if (Objects.isNull(articleContext.getTagName())) {
            return true;
        }
        LambdaQueryWrapper<BlogTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(
                Objects.nonNull(articleContext.getTagName()),
                BlogTag::getTagName,
                articleContext.getTagName()
        );
        List<BlogTag> blogTagList = blogTagService.list(queryWrapper);
        if (!CollectionUtils.isEmpty(blogTagList)) {
            log.error("tag name is exits");
            return false;
        }
        return true;
    }

}
