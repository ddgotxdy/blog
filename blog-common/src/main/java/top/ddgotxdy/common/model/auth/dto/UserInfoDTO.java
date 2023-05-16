package top.ddgotxdy.common.model.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description: 个人信息
 */
@Data
public class UserInfoDTO {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱号")
    private String email;

    @ApiModelProperty("用户真实昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("用户性别（0未知，1男，2女）")
    private Integer sex;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("创建时间")
    private Long createTime;
}
