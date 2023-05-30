package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ddgo
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_comment")
@ApiModel(value = "BlogComment对象", description = "")
public class BlogComment {

    @ApiModelProperty("评论ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long commentId;

    @ApiModelProperty("评论用户ID")
    private Long userId;

    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("父评论id")
    private Long parentId;

    @ApiModelProperty("回复的用户ID")
    private Long replyUserId;

    @ApiModelProperty("是否删除  0否 1是")
    private Integer isDelete;

    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private Integer auditType;

    @ApiModelProperty("评论时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
