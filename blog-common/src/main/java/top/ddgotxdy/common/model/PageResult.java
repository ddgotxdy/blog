package top.ddgotxdy.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description: 分页返回结果类
 */
@ApiModel("分页结果")
@Data
public class PageResult<T> {
    @ApiModelProperty("总页数")
    private Long totalNumber;
    @ApiModelProperty("返回结果")
    private List<T> data;
}
