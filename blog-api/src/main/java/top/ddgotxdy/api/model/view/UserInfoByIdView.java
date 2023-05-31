package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("用户评论得基本信息")
public class UserInfoByIdView {
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("头像")
    private String avatarUrl;
}
