package top.ddgotxdy.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author: ddgo
 * @description: 统一返回id视图
 */
@Data
@Builder
public class IdView {

    @ApiModelProperty("id")
    private Long id;
}