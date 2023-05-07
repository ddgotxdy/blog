package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("图片查询接口")
public class ImageQueryApiParam {
    @ApiModelProperty(value = "图片id")
    private Long imageId;
    @ApiModelProperty(value = "图片名称")
    private String imageName;
//    @ApiModelProperty(value = "是否删除", required = true)
//    private Boolean isDelete;
}
