package top.ddgotxdy.common.model.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ResourcePageListDTO {
    @ApiModelProperty("资源ID")
    private Long resourceId;

    @ApiModelProperty("资源名")
    private String resourceName;

    @ApiModelProperty("请求uri地址")
    private String uri;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("备注")
    private String resourceDesc;
}
