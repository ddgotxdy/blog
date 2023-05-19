package top.ddgotxdy.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("用户名校验参数")
public class UserNameCheckApiModel {
    @ApiModelProperty("用户名")
    @Length(min = 1, max = 20)
    private String username;
}
