package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("网站配置api添加参数")
public class WebsiteConfigAddPaiParam {
    @ApiModelProperty("首页背景图片地址")
    private String homePageUrl;
    @ApiModelProperty("分类背景图片地址")
    private String categoryPageUrl;
    @ApiModelProperty("标签背景图片地址")
    private String tagPageUrl;
    @ApiModelProperty("关于我背景图片地址")
    private String aboutMePageUrl;
}
