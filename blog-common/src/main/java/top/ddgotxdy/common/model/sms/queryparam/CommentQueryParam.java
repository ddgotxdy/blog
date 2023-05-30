package top.ddgotxdy.common.model.sms.queryparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CommentQueryParam {
    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("评论用户ID")
    private Long userId;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private Integer auditType;
}
