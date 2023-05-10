package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.article.ArticleStatus;

import java.util.List;

/**
 * @author: ddgo
 * @description: 文章列表视图类
 */
@Data
public class ArticleBodyPageListView {
    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("文章缩略图，没有则默认填充")
    private String articleCoverUrl;

    @ApiModelProperty("标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("文章状态值 1公开 2私密 3登录可见")
    private ArticleStatus articleStatus;

    @ApiModelProperty("标签名列表")
    private List<String> tagIds;

    @ApiModelProperty("分类名")
    private String categoryId;

    @ApiModelProperty("发表时间")
    private Long createTime;
}
