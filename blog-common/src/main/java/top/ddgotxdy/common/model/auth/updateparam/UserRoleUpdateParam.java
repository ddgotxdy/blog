package top.ddgotxdy.common.model.auth.updateparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description: 用户角色修改参数
 */
@Data
public class UserRoleUpdateParam {
    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不允许为空")
    private Long userId;
    @ApiModelProperty("角色ID")
    @NotNull(message = "角色ID不允许为空")
    private Long roleId;
}
