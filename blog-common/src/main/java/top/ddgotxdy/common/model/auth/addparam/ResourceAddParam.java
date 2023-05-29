package top.ddgotxdy.common.model.auth.addparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ResourceAddParam {
    @ApiModelProperty(value = "用户ID",required = true)
    @NotNull
    private Long userId;

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
