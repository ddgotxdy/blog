package top.ddgotxdy.common.model.auth.updateparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ResourceUpdateParam {
    @ApiModelProperty("用户id")
    @NotNull
    private Long userId;

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
