package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("总数视图")
public class TotalCountView {
    @ApiModelProperty("文章的总数")
    private Long articleCount;
    @ApiModelProperty("分类的总数")
    private Long categoryCount;
    @ApiModelProperty("标签的总数")
    private Long tagCount;
}
