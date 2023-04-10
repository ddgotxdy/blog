package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description: 标签添加参数
 */
@Data
@ApiModel("标签添加参数")
public class TagAddApiParam {
    @ApiModelProperty("标签名称")
    @Length(min = 1, max = 10)
    private String tagName;
}
