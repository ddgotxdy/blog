package top.ddgotxdy.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.dal.entity.BlogCategory;
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

    @Override
    public void execute(ArticleContext articleContext) {
        // 过滤条件不满足
        if (!filter(articleContext)) {
            return;
        }
        // 通过校验，操作日志预落库
        preOplog(articleContext);
        // 开始执行文章操作
        doExecute(articleContext);
        // 已经落库，操作日志实际落库
        afterOplog(articleContext);
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
    protected void preOplog(ArticleContext articleContext) {
        // TODO 操作日志记录
    }

    /**
     * 操作日志后落库
     * @param articleContext 文章上下文
     */
    protected void afterOplog(ArticleContext articleContext) {
        // TODO 操作日志记录
    }

    /**
     * 校验是否是管理员
     * @param articleContext 文章上下文
     * @return true 是管理员 false 否
     */
    protected boolean checkIsAdmin(ArticleContext articleContext) {
        // TODO 代完善，目前只运行1通过
        if (articleContext.getUserId() != 1) {
            return false;
        }
        return true;
    }

    /**
     * 校验分类名称是否唯一
     * @param articleContext 文章上下文
     * @return true 唯一，false 不唯一
     */
    protected boolean checkUniqueCategoryName(ArticleContext articleContext) {
        // 更新的分类具有唯一性，TODO 直接用mysql查，可能包含性能问题
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
        LambdaQueryWrapper<BlogTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(
                Objects.nonNull(articleContext.getCategoryName()),
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
