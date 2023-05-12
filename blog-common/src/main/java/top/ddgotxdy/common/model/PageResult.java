package top.ddgotxdy.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResult<T> {
    @ApiModelProperty("总页数")
    private Long totalPage;
    @ApiModelProperty("返回结果")
    private List<T> data;
}
