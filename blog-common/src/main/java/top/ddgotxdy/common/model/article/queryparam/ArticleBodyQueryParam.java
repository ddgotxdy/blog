package top.ddgotxdy.common.model.article.queryparam;

import io.swagger.annotations.ApiModelProperty;
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
    /**
     * 文章状态值 1公开 2私密 3登录可见
     */
    private Integer articleStatus;
}
