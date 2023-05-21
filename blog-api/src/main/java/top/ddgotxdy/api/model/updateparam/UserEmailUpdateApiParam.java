package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("更新邮箱api参数")
public class UserEmailUpdateApiParam {
    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不允许为空")
    private Long userId;
    @ApiModelProperty("当前密码")
    @Length(min = 6, max = 50)
    private String currentPassword;
    @ApiModelProperty("新邮箱号")
    @Length(min = 6, max = 50)
    private String email;
    @ApiModelProperty("验证码")
    @Length(min = 6, max = 6)
    private String captcha;
}
