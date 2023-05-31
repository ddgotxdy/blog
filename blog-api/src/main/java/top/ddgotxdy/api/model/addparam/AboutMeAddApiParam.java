package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("关于我添加参数")
public class AboutMeAddApiParam {
    @ApiModelProperty("关于我主体")
    @Length(max = 1024, message = "长度超过1024")
    private String aboutMe;
}
