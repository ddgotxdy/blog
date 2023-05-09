package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.sms.SensitiveType;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("敏感词查询参数")
public class SensitiveQueryApiParam {
    @ApiModelProperty("敏感词id")
    private Long sensitiveId;
    @ApiModelProperty("敏感词")
    private String word;
    @ApiModelProperty("敏感类型")
    private SensitiveType sensitiveType;
    @ApiModelProperty("是否删除")
    private Boolean isDelete;
}
