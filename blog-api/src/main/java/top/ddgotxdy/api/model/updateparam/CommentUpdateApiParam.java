package top.ddgotxdy.api.model.updateparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("评论更新参数")
public class CommentUpdateApiParam {
    @ApiModelProperty(value = "评论ID", required = true)
    @NotNull
    private Long commentId;

    @ApiModelProperty("评论内容")
    private String commentContent;
}
