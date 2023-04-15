package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("标签分页视图")
public class TagPageListView {
    @ApiModelProperty("标签ID")
    private Long tagId;

    @ApiModelProperty("标签名")
    private String tagName;

    @ApiModelProperty("创建时间")
    private Long createTime;
}
