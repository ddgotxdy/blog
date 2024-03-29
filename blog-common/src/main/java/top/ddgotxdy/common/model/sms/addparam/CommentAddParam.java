package top.ddgotxdy.common.model.sms.addparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.ddgotxdy.common.annotation.SensitiveWordProperty;

import javax.validation.constraints.NotNull;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CommentAddParam {
    @ApiModelProperty(value = "评论用户ID",required = true)
    @NotNull
    private Long userId;

    @ApiModelProperty(value = "文章id",required = true)
    @NotNull
    private Long articleId;

    @ApiModelProperty(value = "评论内容",required = true)
    @SensitiveWordProperty
    @Length(min = 1, max = 1024, message = "评论长度太长")
    private String commentContent;

    @ApiModelProperty("父评论id")
    private Long parentId;

    @ApiModelProperty("回复的用户ID")
    private Long replyUserId;
}
