package top.ddgotxdy.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: ddgo
 * @description: 文章事件
 */
@Getter
@AllArgsConstructor
public enum ArticleEvent {
    /**
     * 文章主体新增事件
     */
    ARTICLE_BODY_ADD(1),
    /**
     * 文章主体更新事件
     */
    ARTICLE_BODY_UPDATE(2),
    /**
     * 文章主体删除事件
     */
    ARTICLE_BODY_DELETE(3),
    /**
     * 标签新增事件
     */
    TAG_ADD(4),
    /**
     * 标签修改事件
     */
    TAG_UPDATE(5),
    /**
     * 标签删除事件
     */
    TAG_DELETE(6),
    /**
     * 分类新增事件
     */
    CATEGORY_ADD(7),
    /**
     * 分类修改事件
     */
    CATEGORY_UPDATE(8),
    /**
     * 分类新增事件
     */
    CATEGORY_DELETE(9),
    ;
    private final int code;
}
