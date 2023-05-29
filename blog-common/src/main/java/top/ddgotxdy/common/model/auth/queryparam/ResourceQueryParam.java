package top.ddgotxdy.common.model.auth.queryparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ResourceQueryParam {
    @ApiModelProperty("资源ID")
    private Long resourceId;

    @ApiModelProperty("资源名")
    private String resourceName;

    @ApiModelProperty("请求uri地址")
    private String uri;

    @ApiModelProperty("是否删除，0否1是")
    private Boolean isDelete;
}
