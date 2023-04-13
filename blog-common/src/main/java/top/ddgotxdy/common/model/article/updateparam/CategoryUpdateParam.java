package top.ddgotxdy.common.model.article.updateparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CategoryUpdateParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    @Length(min = 1, max = 20)
    private String categoryName;
}
