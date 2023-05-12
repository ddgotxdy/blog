package top.ddgotxdy.common.model.auth.addparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class UserAddParam {
    @ApiModelProperty("用户名")
    @Length(min = 1, max = 20)
    private String username;

    @ApiModelProperty("密码")
    @Length(min = 6, max = 50)
    private String password;

    @ApiModelProperty("邮箱号")
    @Length(min = 6, max = 50)
    private String email;
}
