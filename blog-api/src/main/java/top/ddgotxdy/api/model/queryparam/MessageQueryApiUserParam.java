package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("留言查询参数（用户）")
public class MessageQueryApiUserParam {
    @ApiModelProperty("留言ID")
    private Long messageId;

    @ApiModelProperty("留言内容")
    private String messageContent;
}
