package top.ddgotxdy.common.model.article.queryparam;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ArticleBodyQueryParam {
    /**
     * 文章id
     */
    private Long articleId;
    /**
     * 标题
     */
    private String articleTitle;
    /**
     * 文章内容
     */
    private String articleContent;
    /**
     * 标签的id列表
     */
    private List<Long> tagIds;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 是否删除
     */
    private Boolean isDelete;
}
