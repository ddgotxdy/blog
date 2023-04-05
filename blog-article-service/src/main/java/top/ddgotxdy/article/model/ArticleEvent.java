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
     * 文章新增事件
     */
    ARTICLE_ADD(1),
    /**
     * 文章更新事件
     */
    ARTICLE_UPDATE(2),
    /**
     * 文章删除事件
     */
    ARTICLE_DELETE(3),
    ;
    private final int code;
}
