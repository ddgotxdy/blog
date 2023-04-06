package top.ddgotxdy.common.model.article.addparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 添加文章的参数
 */
@Data
public class ArticleAddParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 文章缩略图
     */
    private String articleCoverUrl;
    /**
     * 标题
     */
    private String articleTitle;
    /**
     * 文章内容
     */
    private String articleContent;
    /**
     * 文章类型 1原创 2转载 3翻译
     */
    private Integer articleType;
    /**
     * 原文链接
     */
    private String originalUrl;
    /**
     * 置顶排序，值越大，排名越高
     */
    private Integer rank;
    /**
     * 文章状态值 1公开 2私密 3登录可见
     */
    private Integer articleStatus;
}
