package top.ddgotxdy.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ddgo
 * @description: id 数据传输对象
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdDTO {
    @ApiModelProperty("id")
    private Long id;
}
