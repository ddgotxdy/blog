package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("图片添加api参数")
public class ImageAddApiParam {
    @ApiModelProperty(value = "图片名称", allowEmptyValue = true)
    @Length(min = 1, max = 20)
    private String imageName;
    @ApiModelProperty(value = "图片url")
    @Length(min = 1, max = 200)
    private String imageUrl;
}
