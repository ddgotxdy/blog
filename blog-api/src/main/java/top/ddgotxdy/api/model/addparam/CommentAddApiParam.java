package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("评论添加参数")
public class CommentAddApiParam {
    @ApiModelProperty(value = "文章id",required = true)
    @NotNull
    private Long articleId;

    @ApiModelProperty(value = "评论内容",required = true)
    @Length(min = 1, max = 1024, message = "评论长度太长")
    private String commentContent;

    @ApiModelProperty("父评论id")
    private Long parentId;

    @ApiModelProperty("回复的用户ID")
    private Long replyUserId;
}
