package top.ddgotxdy.common.model.article.updateparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class TagUpdateParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 标签id
     */
    private Long tagId;
    /**
     * 标签名称
     */
    @Length(min = 1, max = 10)
    private String tagName;
}
