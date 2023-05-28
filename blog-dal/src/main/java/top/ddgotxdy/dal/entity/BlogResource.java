package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ddgo
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_resource")
@ApiModel(value = "BlogResource对象", description = "")
public class BlogResource {

    @ApiModelProperty("资源ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long resourceId;

    @ApiModelProperty("资源名")
    private String resourceName;

    @ApiModelProperty("请求uri地址")
    private String uri;

    @ApiModelProperty("是否删除，0否1是")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @ApiModelProperty("备注")
    private String resourceDesc;
}
