package top.ddgotxdy.common.model.auth.updateparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description: 用户基本信息修改参数
 */
@Data
public class UserInfoUpdateParam {
    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不允许为空")
    private Long userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户真实昵称")
    private String nickname;
    @ApiModelProperty("手机号")
    private String phoneNumber;
    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private Integer sex;
    @ApiModelProperty("头像")
    private String avatarUrl;
}
