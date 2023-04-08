package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author: ddgo
 * @description: 博客实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_article")
@ApiModel(value = "BlogArticle对象", description = "博客实体类")
public class BlogArticle {

    @ApiModelProperty("文章ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long articleId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("文章缩略图，没有则默认填充")
    @TableField(fill = FieldFill.INSERT)
    private String articleCoverUrl;

    @ApiModelProperty("标题")
    private String articleTitle;

    @ApiModelProperty("文章内容")
    private String articleContent;

    @ApiModelProperty("置顶排序，值越大，排名越高")
    private Integer rank;

    @ApiModelProperty("是否删除  0否 1是")
    @TableLogic
    private Boolean isDelete;

    @ApiModelProperty("文章状态值 1公开 2私密 3登录可见")
    private Integer articleStatus;

    @ApiModelProperty("包含的标签id列表对应的json")
    private String tagIds;

    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty("发表时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
