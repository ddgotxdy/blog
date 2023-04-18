package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("标签更新参数api")
public class TagUpdateApiParam {
    @ApiModelProperty("标签id")
    private Long tagId;
    @ApiModelProperty("标签名称")
    private String tagName;
}
