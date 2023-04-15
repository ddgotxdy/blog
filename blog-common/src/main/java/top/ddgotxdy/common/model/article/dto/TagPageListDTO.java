package top.ddgotxdy.common.model.article.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description: admin分页查询列表视图
 */
@Data
public class TagPageListDTO {
    @ApiModelProperty("标签ID")
    private Long tagId;

    @ApiModelProperty("标签名")
    private String tagName;

    @ApiModelProperty("创建时间")
    private Long createTime;
}
