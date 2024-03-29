package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.article.ArticleStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: ddgo
 * @description: 添加文章请求参数
 */
@Data
@ApiModel("添加文章请求参数")
public class ArticleBodyAddApiParam {
    @ApiModelProperty("文章缩略图，没有则默认填充")
    @Size(min = 1, max = 1024)
    private String articleCoverUrl;

    @ApiModelProperty("标题")
    @Size(min = 1, max = 50)
    private String articleTitle;

    @ApiModelProperty("文章内容")
    @NotBlank
    private String articleContent;

    @ApiModelProperty("置顶排序，值越大，排名越高")
    private Integer rank;

    @ApiModelProperty("文章状态值 1公开 2私密 3登录可见")
    private ArticleStatus articleStatus;

    @ApiModelProperty("标签的id列表")
    @Size(min = 1, max = 3)
    private List<Long> tagIds;

    @ApiModelProperty("分类id")
    @NotNull
    private Long categoryId;
}
