package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author: ddgo
 * @description: 博客文章实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_image")
@ApiModel(value = "BlogImage对象", description = "")
public class BlogImage {
    @ApiModelProperty("图片ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long imageId;

    @ApiModelProperty("图片名")
    private String imageName;

    @ApiModelProperty("图片url地址")
    private String imageUrl;

    @ApiModelProperty("是否删除  0否 1是")
    private Boolean isDelete;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
