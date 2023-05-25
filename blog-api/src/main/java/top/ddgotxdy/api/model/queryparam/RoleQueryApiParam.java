package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("角色分页查询参数")
public class RoleQueryApiParam {
    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("是否删除，0否1是")
    private Boolean isDelete;

    @ApiModelProperty("包含的权限，例如[1001,1002,1003]")
    private List<Long> menuIds;
}
