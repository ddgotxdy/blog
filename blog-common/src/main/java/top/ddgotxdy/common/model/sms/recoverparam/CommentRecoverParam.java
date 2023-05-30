package top.ddgotxdy.common.model.sms.recoverparam;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CommentRecoverParam {
    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("需要恢复的id列表")
    private List<Long> commentIds;
}
