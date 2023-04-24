package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("图片更新api参数")
public class ImageUpdateApiParam {
    @ApiModelProperty(value = "图片名称", allowEmptyValue = true)
    private String imageName;
}
