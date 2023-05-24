package top.ddgotxdy.common.model.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class RolePageListDTO {
    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("是否删除，0否1是")
    private Boolean isDelete;

    @ApiModelProperty("包含的权限，例如[1001,1002,1003]")
    private List<Long> menuIds;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("备注")
    private String roleDesc;
}
