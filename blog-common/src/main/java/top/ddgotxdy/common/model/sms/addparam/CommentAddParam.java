package top.ddgotxdy.common.model.sms.addparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.ddgotxdy.common.annotation.SensitiveWordProperty;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CommentAddParam {
    @ApiModelProperty("评论用户ID")
    private Long userId;

    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("评论内容")
    @SensitiveWordProperty
    @Length(min = 1, max = 1024, message = "评论长度太长")
    private String commentContent;

    @ApiModelProperty("父评论id")
    private Integer parentId;
}
