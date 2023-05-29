package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("角色添加参数")
public class RoleAddApiParam {
    @ApiModelProperty("角色名称")
    @Length(min = 2, max = 10, message = "角色名称在长度在2-10之间")
    private String roleName;

    @ApiModelProperty("包含的权限，例如[1001,1002,1003]")
    private List<Long> menuIds;

    @ApiModelProperty("包含的资源，例如[1001,1002,1003]")
    private List<Long> resourceIds;

    @ApiModelProperty("备注")
    @Length(max = 512, message = "描述长度超过512")
    private String roleDesc;
}
