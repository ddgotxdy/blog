package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("资源分页列表视图")
public class ResourcePageListView {
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
