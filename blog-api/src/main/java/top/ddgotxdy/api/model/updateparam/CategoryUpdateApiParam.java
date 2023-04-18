package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("分类更新参数api")
public class CategoryUpdateApiParam {
    @ApiModelProperty("分类id")
    private Long categoryId;
    @ApiModelProperty("分类名称")
    private String categoryName;
}
