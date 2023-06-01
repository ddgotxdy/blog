package top.ddgotxdy.api.model.view;

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
@ApiModel("操作日志分页查询")
public class OplogPageListView {
    @ApiModelProperty("操作id")
    private Long operatorId;

    @ApiModelProperty("操作用户ID")
    private Long userId;

    @ApiModelProperty("操作内容")
    private String operatorContent;

    @ApiModelProperty("操作类型 1文章新增")
    private OplogType operatorType;

    @ApiModelProperty("操作阶段 1预操作 2操作成功")
    private OplogStage operatorStage;

    @ApiModelProperty("操作时间")
    private Long createTime;
}
