package top.ddgotxdy.api.model.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.sms.SensitiveType;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("敏感词分页列表视图")
public class SensitivePageListView {
    @ApiModelProperty("敏感词id")
    private Long sensitiveId;
    @ApiModelProperty("敏感词")
    private String word;
    @ApiModelProperty("敏感类型")
    private SensitiveType sensitiveType;
    @ApiModelProperty("创建时间")
    private Long createTime;
}
