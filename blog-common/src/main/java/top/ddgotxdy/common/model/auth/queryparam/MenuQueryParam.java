package top.ddgotxdy.common.model.auth.queryparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class MenuQueryParam {
    @ApiModelProperty("菜单ID")
    private Long menuId;

    @ApiModelProperty("菜单名")
    private String menuName;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("是否删除，0否1是")
    private Boolean isDelete;
}
