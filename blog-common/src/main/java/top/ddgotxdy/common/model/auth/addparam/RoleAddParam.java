package top.ddgotxdy.common.model.auth.addparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class RoleAddParam {
    @ApiModelProperty("用户id")
    @NotNull
    private Long userId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("包含的权限，例如[1001,1002,1003]")
    private String menuIds;

    @ApiModelProperty("备注")
    private String roleDesc;
}
