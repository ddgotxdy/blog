package top.ddgotxdy.article.annotation;

import top.ddgotxdy.article.model.ArticleEvent;

import java.lang.annotation.*;

/**
 * @author: ddgo
 * @description: 文章事件分发器
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArticleEventSelector {
    ArticleEvent value();
}
