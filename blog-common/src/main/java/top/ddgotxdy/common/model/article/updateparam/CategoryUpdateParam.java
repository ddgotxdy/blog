package top.ddgotxdy.common.model.article.updateparam;

import lombok.Data;

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
    private String categoryName;
}
