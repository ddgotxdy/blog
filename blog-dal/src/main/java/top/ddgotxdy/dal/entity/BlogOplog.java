package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author: ddgo
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_oplog")
@ApiModel(value = "BlogOplog对象", description = "")
public class BlogOplog {

    @ApiModelProperty("操作id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long operatorId;

    @ApiModelProperty("操作用户ID")
    private Long userId;

    @ApiModelProperty("操作内容")
    private String operatorContent;

    @ApiModelProperty("操作类型 1文章新增")
    private Integer operatorType;

    @ApiModelProperty("操作阶段 1预操作 2操作成功")
    private Integer operatorStage;

    @ApiModelProperty("操作时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
}
