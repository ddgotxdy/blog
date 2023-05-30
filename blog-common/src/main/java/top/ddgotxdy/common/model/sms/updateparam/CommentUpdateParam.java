package top.ddgotxdy.common.model.sms.updateparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.annotation.SensitiveWordProperty;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CommentUpdateParam {
    @ApiModelProperty(value = "评论ID", required = true)
    @NotNull
    private Long commentId;

    @ApiModelProperty(value = "评论用户ID", required = true)
    @NotNull
    private Long userId;

    @ApiModelProperty("评论内容")
    @SensitiveWordProperty
    private String commentContent;

    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private Integer auditType;
}
