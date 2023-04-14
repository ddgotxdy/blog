package top.ddgotxdy.article.convert;

import org.springframework.beans.BeanUtils;
import top.ddgotxdy.article.model.ArticleContext;
import top.ddgotxdy.dal.entity.BlogArticle;
import top.ddgotxdy.dal.entity.BlogCategory;
import top.ddgotxdy.dal.entity.BlogTag;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @author: ddgo
 * @description: ArticleContext转实体对象
 */
public class Context2EntityConvert {
    private Context2EntityConvert() { }

    /**
     * ArticleContext  <===> Article
     * @param articleContext 文章上下文
     * @return Article
     */
    public static BlogArticle articleContext2ArticleForAdd(ArticleContext articleContext) {
        // 目前直接beanUtils就行
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(articleContext, blogArticle);
        // 标签的值
        List<Long> tagIds = Optional.ofNullable(articleContext.getTagIds())
                .orElse(Collections.emptyList());
        String tagIdsString = toJSONString(tagIds);
        blogArticle.setTagIds(tagIdsString);
        return blogArticle;
    }

    /**
     * ArticleContext  <===> Article
     * @param articleContext 文章上下文
     * @return Article
     */
    public static BlogArticle articleContext2ArticleForUpdate(ArticleContext articleContext) {
        // 目前直接beanUtils就行
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(articleContext, blogArticle);
        // 标签的值
        if (Objects.nonNull(articleContext.getTagIds())) {
            String tagIdsString = toJSONString(articleContext.getTagIds());
            blogArticle.setTagIds(tagIdsString);
        }
        return blogArticle;
    }

    /**
     * ArticleContext  <===> Tag
     * @param articleContext 文章上下文
     * @return BlogTag
     */
    public static BlogTag articleContext2Tag(ArticleContext articleContext) {
        // 目前直接beanUtils就行
        BlogTag blogTag = new BlogTag();
        BeanUtils.copyProperties(articleContext, blogTag);
        return blogTag;
    }

    /**
     * ArticleContext  <===> Category
     * @param articleContext 文章上下文
     * @return Article
     */
    public static BlogCategory articleContext2Category(ArticleContext articleContext) {
        // 目前直接beanUtils就行
        BlogCategory blogCategory = new BlogCategory();
        BeanUtils.copyProperties(articleContext, blogCategory);
        return blogCategory;
    }
}
