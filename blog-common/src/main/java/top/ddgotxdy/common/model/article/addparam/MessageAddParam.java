package top.ddgotxdy.common.model.article.addparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.ddgotxdy.common.annotation.SensitiveWord;

/**
 * @author: ddgo
 * @description: 留言添加参数
 */
@Data
public class MessageAddParam {
    /**
     * 留言内容
     */
    @Length(min = 1, max = 50)
    @SensitiveWord
    private String messageContent;
}
