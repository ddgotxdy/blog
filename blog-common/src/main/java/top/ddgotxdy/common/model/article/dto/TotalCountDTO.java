package top.ddgotxdy.common.model.article.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class TotalCountDTO {
    @ApiModelProperty("文章的总数")
    private Long articleCount;
    @ApiModelProperty("分类的总数")
    private Long categoryCount;
    @ApiModelProperty("标签的总数")
    private Long tagCount;
}
