package top.ddgotxdy.api.model.queryparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("评论查询api参数（用户）")
public class CommentQueryApiUserParam {
    @ApiModelProperty("文章ID")
    private Long articleId;
}
