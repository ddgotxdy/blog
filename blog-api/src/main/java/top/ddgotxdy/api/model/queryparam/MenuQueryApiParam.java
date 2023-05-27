package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("菜单查询api参数")
public class MenuQueryApiParam {
    @ApiModelProperty("菜单ID")
    private Long menuId;

    @ApiModelProperty("菜单名")
    private String menuName;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("是否删除，0否1是")
    private Boolean isDelete;
}
