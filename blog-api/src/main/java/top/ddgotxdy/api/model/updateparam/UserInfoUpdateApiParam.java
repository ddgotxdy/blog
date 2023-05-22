package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.auth.SexEnum;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("用户信息更新参数")
public class UserInfoUpdateApiParam {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户真实昵称")
    private String nickname;
    @ApiModelProperty("手机号")
    private String phoneNumber;
    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private SexEnum sex;
    @ApiModelProperty("头像")
    private String avatarUrl;
}
