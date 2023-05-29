package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("资源更新参数")
public class ResourceUpdateApiParam {
    @ApiModelProperty("资源ID")
    @NotNull
    private Long resourceId;

    @ApiModelProperty("资源名")
    private String resourceName;

    @ApiModelProperty("请求uri地址")
    private String uri;

    @ApiModelProperty("备注")
    private String resourceDesc;
}
