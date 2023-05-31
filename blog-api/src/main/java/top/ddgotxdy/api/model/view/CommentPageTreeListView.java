package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("评论树形分页视图")
public class CommentPageTreeListView {
    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("评论用户ID")
    private Long userId;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("回复评论用户ID")
    private Long replyUserId;

    @ApiModelProperty("评论时间")
    private Long createTime;

    @ApiModelProperty("评论子节点")
    private List<CommentPageTreeListView> children;
}
