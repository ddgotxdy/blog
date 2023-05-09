package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.ddgotxdy.common.enums.sms.SensitiveType;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("敏感词添加api接口")
public class SensitiveAddApiParam {
    @ApiModelProperty("敏感词")
    @Length(min = 1, max = 50)
    private String word;
    @ApiModelProperty("敏感类型")
    private SensitiveType sensitiveType;
}
