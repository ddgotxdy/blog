package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("留言分页视图（用户）")
public class MessagePageListUserView {
    @ApiModelProperty("留言ID")
    private Long messageId;
    @ApiModelProperty("留言内容")
    private String messageContent;
    @ApiModelProperty("留言时间")
    private Long createTime;
}
