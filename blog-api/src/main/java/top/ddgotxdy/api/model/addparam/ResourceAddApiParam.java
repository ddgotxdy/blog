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
@ApiModel("资源添加参数")
public class ResourceAddApiParam {
    @ApiModelProperty(value = "资源名",required = true)
    @Length(min = 2, max = 64, message = "资源名长度在2-64")
    private String resourceName;

    @ApiModelProperty(value = "请求uri地址", required = true)
    @Length(min = 1, max = 256, message = "uri长度在1-256")
    private String uri;

    @ApiModelProperty("备注")
    @Length(max = 512, message = "备注长度不超过512")
    private String resourceDesc;
}
