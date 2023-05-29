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
@ApiModel("菜单分页列表视图")
public class MenuPageListView {
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

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("备注")
    private String menuDesc;

    @ApiModelProperty("当前菜单的子节点")
    private List<MenuPageListView> children;
}
