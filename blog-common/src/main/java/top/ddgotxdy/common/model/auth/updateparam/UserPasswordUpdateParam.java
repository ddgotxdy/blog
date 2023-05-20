package top.ddgotxdy.common.model.auth.updateparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description: 用户密码修改参数
 */
@Data
public class UserPasswordUpdateParam {
    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不允许为空")
    private Long userId;
    @ApiModelProperty("当前密码")
    @Length(min = 6, max = 50)
    private String currentPassword;
    @ApiModelProperty("新密码")
    @Length(min = 6, max = 50)
    private String password;
    @ApiModelProperty("重复新密码")
    @Length(min = 6, max = 50)
    private String rePassword;
}
