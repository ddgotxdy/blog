package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author: ddgo
 * @description: 博客敏感词实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_sensitive")
@ApiModel(value = "BlogSensitive对象", description = "")
public class BlogSensitive {

    @ApiModelProperty("敏感ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long sensitiveId;

    @ApiModelProperty("敏感词")
    private String word;

    @ApiModelProperty("敏感类型 0 deny 1 allow")
    private Integer sensitiveType;

    @ApiModelProperty("是否删除  0否 1是")
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
