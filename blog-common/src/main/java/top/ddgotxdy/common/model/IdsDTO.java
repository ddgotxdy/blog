package top.ddgotxdy.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdsDTO {
    @ApiModelProperty("未成功的ids列表")
    private List<Long> ids;
}
