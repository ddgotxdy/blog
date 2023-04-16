package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author: ddgo
 * @description: 博客分类实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_category")
@ApiModel(value = "BlogCategory对象", description = "博客分类实体类")
public class BlogCategory {

    @ApiModelProperty("分类ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long categoryId;

    @ApiModelProperty("分类名")
    private String categoryName;

    @ApiModelProperty("是否删除  0否 1是")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
