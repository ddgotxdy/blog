package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author: ddgo
 * @description: 博客标签实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_tag")
@ApiModel(value = "BlogTag对象", description = "标签实体类")
public class BlogTag {

    @ApiModelProperty("标签ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long tagId;

    @ApiModelProperty("标签名")
    private String tagName;

    @ApiModelProperty("是否删除  0否 1是")
    @TableLogic
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;


}
