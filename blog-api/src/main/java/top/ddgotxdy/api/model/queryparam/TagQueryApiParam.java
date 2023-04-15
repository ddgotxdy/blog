package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("标签分页查询参数api")
public class TagQueryApiParam {
    @ApiModelProperty("标签id")
    private Long tagId;
    @ApiModelProperty("标签名称")
    private String tagName;
    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}
