package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ArticleBodyPageListUserView {
    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("文章缩略图，没有则默认填充")
    private String articleCoverUrl;

    @ApiModelProperty("标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("标签id列表")
    private List<Long> tagIds;

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("rank")
    private Integer rank;

    @ApiModelProperty("发表时间")
    private Long createTime;
}
