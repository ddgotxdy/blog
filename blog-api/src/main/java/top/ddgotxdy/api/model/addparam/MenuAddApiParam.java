package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("菜单添加api参数")
public class MenuAddApiParam {
    @ApiModelProperty(value = "菜单名", required = true)
    @Length(min = 2, max = 64, message = "菜单名长度在2-64之间")
    private String menuName;

    @ApiModelProperty("路由地址")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty(value = "图标",required = true)
    @Length(min = 1, max = 128, message = "图标长度在1-128之间")
    private String icon;

    @ApiModelProperty(value = "父菜单Id，没有默认为null")
    private Long parentId;

    @ApiModelProperty("备注")
    private String menuDesc;
}
