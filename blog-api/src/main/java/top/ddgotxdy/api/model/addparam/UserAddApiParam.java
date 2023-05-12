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
@ApiModel("用户注册参数")
public class UserAddApiParam {
    @ApiModelProperty("用户名")
    @Length(min = 1, max = 20)
    private String username;

    @ApiModelProperty("密码")
    @Length(min = 6, max = 50)
    private String password;

    @ApiModelProperty("重复密码")
    @Length(min = 6, max = 50)
    private String rePassword;

    @ApiModelProperty("邮箱号")
    @Length(min = 6, max = 50)
    private String email;

    @ApiModelProperty("验证码")
    @Length(min = 6, max = 6)
    private String captcha;
}
