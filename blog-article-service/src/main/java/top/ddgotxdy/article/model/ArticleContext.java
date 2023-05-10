package top.ddgotxdy.article.model;

import lombok.Data;

import java.util.List;

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
     * 文章id列表
     */
    private List<Long> articleIds;
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
     * 当前文章的置顶排名
     */
    private Integer rank;
    /**
     * 文章状态值
     */
    private Integer articleStatus;
    /**
     * 标签的id列表
     */
    private List<Long> tagIds;
    /**
     * 标签的id
     */
    private Long tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类id列表
     */
    private List<Long> categoryIds;
}
