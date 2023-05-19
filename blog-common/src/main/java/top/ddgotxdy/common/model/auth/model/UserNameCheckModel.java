package top.ddgotxdy.common.model.auth.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class UserNameCheckModel {
    @ApiModelProperty("用户名")
    @Length(min = 1, max = 20)
    private String username;
}
