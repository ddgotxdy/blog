package top.ddgotxdy.common.model.sms.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CommentPageListDTO {
    @ApiModelProperty("评论ID")
    private Long commentId;

    @ApiModelProperty("评论用户ID")
    private Long userId;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("审核类型 0审核中 1审核通过 2审核失败")
    private Integer auditType;

    @ApiModelProperty("评论时间")
    private Long createTime;

    @ApiModelProperty("评论子节点")
    private List<CommentPageListDTO> children;
}
