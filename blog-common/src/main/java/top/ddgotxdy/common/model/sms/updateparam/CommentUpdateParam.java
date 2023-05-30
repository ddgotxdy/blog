package top.ddgotxdy.common.model.sms.updateparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.annotation.SensitiveWordProperty;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CommentUpdateParam {
    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("评论用户ID")
    private Long userId;

    @ApiModelProperty("评论内容")
    @SensitiveWordProperty
    private String commentContent;

    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private Integer auditType;
}
