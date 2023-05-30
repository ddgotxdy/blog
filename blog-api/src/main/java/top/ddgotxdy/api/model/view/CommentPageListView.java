package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.sms.AuditType;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("评论分页视图")
public class CommentPageListView {
    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("评论用户ID")
    private Long userId;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private AuditType auditType;

    @ApiModelProperty("评论时间")
    private Long createTime;
}
