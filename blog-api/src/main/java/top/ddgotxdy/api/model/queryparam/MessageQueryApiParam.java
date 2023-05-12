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
@ApiModel("留言查询参数")
public class MessageQueryApiParam {
    @ApiModelProperty("留言ID")
    private Long messageId;

    @ApiModelProperty("留言内容")
    private String messageContent;

    @ApiModelProperty("审核类型 1审核中 2审核通过 3审核失败")
    private AuditType auditType;
}
