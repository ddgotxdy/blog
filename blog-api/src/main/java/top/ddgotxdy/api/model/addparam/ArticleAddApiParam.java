package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("添加文章请求参数")
public class ArticleAddApiParam {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("文章缩略图，没有则默认填充")
    private String articleCoverUrl;

    @ApiModelProperty("标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章类型 1原创 2转载 3翻译")
    private Integer articleType;

    @ApiModelProperty("原文链接")
    private String originalUrl;

    @ApiModelProperty("置顶排序，值越大，排名越高")
    private Integer rank;

    @ApiModelProperty("文章状态值 1公开 2私密 3登录可见")
    private Integer articleStatus;
}
