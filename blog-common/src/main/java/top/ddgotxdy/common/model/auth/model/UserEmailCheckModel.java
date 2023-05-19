package top.ddgotxdy.common.model.auth.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class UserEmailCheckModel {
    @ApiModelProperty("邮箱号")
    @Length(min = 6, max = 50)
    private String email;
}
