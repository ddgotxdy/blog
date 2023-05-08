package top.ddgotxdy.dal.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ddgo
 * @description: 博客留言实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_message")
@ApiModel(value = "BlogMessage对象", description = "")
public class BlogMessage {

    @ApiModelProperty("留言ID")
    @TableId(type = IdType.ASSIGN_ID)
    private Long messageId;

    @ApiModelProperty("留言内容")
    private String messageContent;

    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private Integer auditType;

    @ApiModelProperty("留言时间")
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
