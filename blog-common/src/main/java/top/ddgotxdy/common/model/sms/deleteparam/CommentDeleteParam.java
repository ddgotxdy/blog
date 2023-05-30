package top.ddgotxdy.common.model.sms.deleteparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CommentDeleteParam {
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("需要删除的id列表")
    private List<Long> commentIds;
}
