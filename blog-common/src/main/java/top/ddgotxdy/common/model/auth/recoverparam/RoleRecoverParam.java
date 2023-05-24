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
public class RoleRecoverParam {
    @ApiModelProperty("用户id")
    @NotNull
    private Long userId;

    @ApiModelProperty("角色id列表")
    @NotNull
    private List<Long> roleIds;
}
