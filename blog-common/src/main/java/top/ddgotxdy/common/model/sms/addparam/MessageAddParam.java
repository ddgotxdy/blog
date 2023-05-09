package top.ddgotxdy.common.model.sms.addparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.ddgotxdy.common.annotation.SensitiveWord;
import top.ddgotxdy.common.annotation.SensitiveWordProperty;

/**
 * @author: ddgo
 * @description: 留言添加参数
 */
@Data
@SensitiveWord
public class MessageAddParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 留言内容
     */
    @Length(min = 1, max = 50)
    @SensitiveWordProperty
    private String messageContent;
}
