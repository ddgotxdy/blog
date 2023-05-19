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
@ApiModel("邮箱校验参数")
public class UserEmailCheckApiModel {
    @ApiModelProperty("邮箱号")
    @Length(min = 6, max = 50)
    private String email;
}
