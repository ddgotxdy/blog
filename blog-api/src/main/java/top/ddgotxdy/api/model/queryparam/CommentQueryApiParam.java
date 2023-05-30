package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.sms.AuditType;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("评论查询api参数（管理）")
public class CommentQueryApiParam {
    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("评论用户ID")
    private Long userId;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private AuditType auditType;
}
