package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("角色更新api参数")
public class RoleUpdateApiParam {
    @ApiModelProperty("角色id")
    @NotNull
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("包含的权限，例如[1001,1002,1003]")
    private List<Long> menuIds;

    @ApiModelProperty("包含的资源，例如[1001,1002,1003]")
    private List<Long> resourceIds;

    @ApiModelProperty("备注")
    private String roleDesc;
}
