package top.ddgotxdy.common.model.article.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    @ApiModelProperty("文章类型 1原创 2转载 3翻译")
    private Integer articleType;

    @ApiModelProperty("原文链接")
    private String originalUrl;

    @ApiModelProperty("文章状态值 1公开 2私密 3登录可见")
    private Integer articleStatus;

    @ApiModelProperty("发表时间")
    private Long createTime;

    @ApiModelProperty("更新时间")
    private Long updateTime;
}
