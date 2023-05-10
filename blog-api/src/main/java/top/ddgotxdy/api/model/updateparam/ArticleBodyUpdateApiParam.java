package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.article.ArticleStatus;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("文章更新参数")
public class ArticleBodyUpdateApiParam {
    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("文章缩略图，没有则默认填充")
    private String articleCoverUrl;

    @ApiModelProperty("标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("置顶排序，值越大，排名越高")
    private Integer rank;

    @ApiModelProperty("文章状态值 1公开 2私密 3登录可见")
    private ArticleStatus articleStatus;

    @ApiModelProperty("标签的id列表")
    private List<Long> tagIds;

    @ApiModelProperty("分类id")
    private Long categoryId;
}
