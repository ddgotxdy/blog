package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("菜单更新api参数")
public class MenuUpdateApiParam {
    @ApiModelProperty("菜单ID")
    @NotNull
    private Long menuId;

    @ApiModelProperty("菜单名")
    private String menuName;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("备注")
    private String menuDesc;
}
