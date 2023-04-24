package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("图片分页视图")
public class ImagePageListView {
    @ApiModelProperty("图片ID")
    private Long imageId;

    @ApiModelProperty("图片名")
    private String imageName;

    @ApiModelProperty("图片地址")
    private String imageUrl;

    @ApiModelProperty("创建时间")
    private Long createTime;
}
