package top.ddgotxdy.common.model.article.updateparam;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ArticleBodyUpdateParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户id
     */
    private Long articleId;
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
     * 置顶排序，值越大，排名越高
     */
    private Integer rank;
    /**
     * 文章状态值 1公开 2私密 3登录可见
     */
    private Integer articleStatus;
    /**
     * 标签的id列表
     */
    private List<Long> tagIds;
    /**
     * 分类id
     */
    private Long categoryId;
}
