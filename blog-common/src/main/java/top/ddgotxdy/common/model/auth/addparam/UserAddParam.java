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
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    @Length(min = 1, max = 20)
    private String username;

    @ApiModelProperty("密码")
    @Length(min = 6, max = 50)
    private String password;

    @ApiModelProperty("邮箱号")
    @Length(min = 6, max = 50)
    private String email;

    @ApiModelProperty("用户真实昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private Integer sex;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色ID")
    private Long roleId;
}
