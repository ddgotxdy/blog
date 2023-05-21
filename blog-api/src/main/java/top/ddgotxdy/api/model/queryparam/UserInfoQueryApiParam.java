package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.auth.SexEnum;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("用户基本信息查询api参数")
public class UserInfoQueryApiParam {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("性别")
    private SexEnum sex;
    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}
