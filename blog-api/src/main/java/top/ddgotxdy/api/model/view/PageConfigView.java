package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("网站配置视图")
public class PageConfigView {
    @ApiModelProperty("首页背景图片地址")
    private String homePageUrl;
    @ApiModelProperty("分类背景图片地址")
    private String categoryPageUrl;
    @ApiModelProperty("标签背景图片地址")
    private String tagPageUrl;
    @ApiModelProperty("关于我背景图片地址")
    private String aboutMePageUrl;
    @ApiModelProperty("留言背景图片地址")
    private String messagePageUrl;
    @ApiModelProperty("网站作者头像地址")
    private String authorUrl;
    @ApiModelProperty("网站作者名称")
    private String authorName;
    @ApiModelProperty("网站简介")
    private String briefIntroduction;
    @ApiModelProperty("qq地址")
    private String qqUrl;
    @ApiModelProperty("github地址")
    private String githubUrl;
}
