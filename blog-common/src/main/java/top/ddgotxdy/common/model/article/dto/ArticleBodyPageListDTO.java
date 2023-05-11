package top.ddgotxdy.common.model.article.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description: 博客列表类
 */
@Data
public class ArticleBodyPageListDTO {
    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("文章缩略图，没有则默认填充")
    private String articleCoverUrl;

    @ApiModelProperty("标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章状态值 1公开 2私密 3登录可见")
    private Integer articleStatus;

    @ApiModelProperty("标签id列表")
    private List<Long> tagIds;

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("rank")
    private Integer rank;

    @ApiModelProperty("发表时间")
    private Long createTime;
}
