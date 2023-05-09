package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.sms.AuditType;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("留言更新参数")
public class MessageUpdateApiParam {
    @ApiModelProperty("留言id")
    private Long messageId;
    @ApiModelProperty("审核状态")
    private AuditType auditType;
}
