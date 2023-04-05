package top.ddgotxdy.article.model;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 文章上下文
 */
@Data
public class ArticleContext {
    /**
     * 文章的事件
     */
    private ArticleEvent articleEvent;
    /**
     * 文章的id
     */
    private Long articleId;
    /**
     * 用户的id
     */
    private Long userId;
    /**
     * 文章缩略图，没有则默认填充
     */
    private String articleCoverUrl;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章内容
     */
    private String articleContent;
    /**
     * 文章的类型
     */
    private Integer articleType;
    /**
     * 原始链接，这里是转载链接
     */
    private String originalUrl;
    /**
     * 当前文章的置顶排名
     */
    private Integer rank;
    /**
     * 文章状态值
     */
    private Integer articleStatus;
}
