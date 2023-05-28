package top.ddgotxdy.common.model.auth.addparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class MenuAddParam {
    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "菜单名", required = true)
    private String menuName;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty(value = "图标",required = true)
    private String icon;

    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty("备注")
    private String menuDesc;
}
