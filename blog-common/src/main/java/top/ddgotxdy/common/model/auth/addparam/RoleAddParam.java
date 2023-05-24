package top.ddgotxdy.common.model.auth.addparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Length(min = 2, max = 10, message = "角色名称在长度在2-10之间")
    private String roleName;

    @ApiModelProperty("包含的权限，例如[1001,1002,1003]")
    private List<Long> menuIds;

    @ApiModelProperty("备注")
    @Length(max = 512, message = "描述长度超过512")
    private String roleDesc;
}
