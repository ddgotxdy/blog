package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("用户更新密码api参数")
public class UserPasswordUpdateApiParam {
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
