package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.auth.SexEnum;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("用户基本信息分页查询列表视图")
public class UserInfoPageListView {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("邮箱号")
    private String email;

    @ApiModelProperty("用户真实昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String phoneNumber;

    @ApiModelProperty("用户性别对应枚举，用户性别（0未知，1男，2女）")
    private SexEnum sexEnum;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色id")
    private Long roleId;

    @ApiModelProperty("创建时间")
    private Long createTime;
}
