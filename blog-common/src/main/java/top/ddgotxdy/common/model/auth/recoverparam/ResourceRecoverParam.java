package top.ddgotxdy.common.model.auth.recoverparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ResourceRecoverParam {
    @ApiModelProperty("用户id")
    @NotNull
    private Long userId;

    @ApiModelProperty("资源id列表")
    @NotNull
    private List<Long> resourceIds;
}
