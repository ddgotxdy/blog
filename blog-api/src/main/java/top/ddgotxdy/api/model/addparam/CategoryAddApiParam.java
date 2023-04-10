package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description: 分类添加参数
 */
@Data
@ApiModel("分类添加参数")
public class CategoryAddApiParam {
    @ApiModelProperty("分类名称")
    @Length(min = 1, max = 20)
    private String categoryName;
}
