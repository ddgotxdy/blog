package top.ddgotxdy.common.model.article.addparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description: 分类新增参数
 */
@Data
public class CategoryAddParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 分类名称
     */
    @Length(min = 1, max = 20)
    private String categoryName;
}
