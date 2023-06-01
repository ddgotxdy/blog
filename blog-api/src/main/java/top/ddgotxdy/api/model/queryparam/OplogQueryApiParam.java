package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.ddgotxdy.common.enums.OplogStage;
import top.ddgotxdy.common.enums.OplogType;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("操作日志查询")
public class OplogQueryApiParam {
    @ApiModelProperty("操作id")
    private Long operatorId;

    @ApiModelProperty("操作用户ID")
    private Long userId;

    @ApiModelProperty("操作类型 1文章新增")
    private OplogType operatorType;

    @ApiModelProperty("操作阶段 1预操作 2操作成功")
    private OplogStage operatorStage;
}
