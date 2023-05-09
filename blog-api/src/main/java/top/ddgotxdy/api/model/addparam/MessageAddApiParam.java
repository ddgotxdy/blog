package top.ddgotxdy.api.model.addparam;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
@ApiModel("留言添加参数")
public class MessageAddApiParam {
    @ApiModelProperty("留言内容")
    @Length(min = 1, max = 50)
    private String messageContent;
}
