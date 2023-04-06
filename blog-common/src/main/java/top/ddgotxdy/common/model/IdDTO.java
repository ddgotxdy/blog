package top.ddgotxdy.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author: ddgo
 * @description: id 数据传输对象
 */
@Data
@Builder
public class IdDTO {
    @ApiModelProperty("id")
    private Long id;
}
