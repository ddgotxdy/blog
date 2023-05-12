package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@ApiModel("验证码请求参数")
@Data
public class CaptchaSendApiParam {
    @ApiModelProperty("邮箱")
    @Length(min = 1, max = 30)
    private String mail;
}
