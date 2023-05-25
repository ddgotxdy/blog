package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("角色分页列表")
public class RolePageListView {
    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("包含的权限，例如[1001,1002,1003]")
    private List<Long> menuIds;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("备注")
    private String roleDesc;
}
