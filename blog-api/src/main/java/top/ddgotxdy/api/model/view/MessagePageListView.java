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
@ApiModel("留言分页视图")
public class MessagePageListView {
    @ApiModelProperty("留言ID")
    private Long messageId;
    @ApiModelProperty("留言内容")
    private String messageContent;
    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private AuditType auditType;
    @ApiModelProperty("留言时间")
    private Long createTime;
}
