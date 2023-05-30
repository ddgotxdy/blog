package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.sms.AuditType;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("评论审核api参数")
public class CommentAuditApiParam {
    @ApiModelProperty(value = "评论ID", required = true)
    @NotNull
    private Long commentId;

    @ApiModelProperty(value = "审核类型 0审核中 1审核通过 2审核失败", required = true)
    @NotNull
    private AuditType auditType;
}
