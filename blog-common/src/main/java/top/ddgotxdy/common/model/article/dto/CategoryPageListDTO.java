package top.ddgotxdy.common.model.article.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CategoryPageListDTO {
    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("分类名")
    private String categoryName;

    @ApiModelProperty("创建时间")
    private Long createTime;
}
