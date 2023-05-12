package top.ddgotxdy.common.model.auth.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class UserLoginModel {
    @ApiModelProperty("用户名")
    @Length(min = 1, max = 20)
    private String username;

    @ApiModelProperty("密码")
    @Length(min = 6, max = 50)
    private String password;
}
