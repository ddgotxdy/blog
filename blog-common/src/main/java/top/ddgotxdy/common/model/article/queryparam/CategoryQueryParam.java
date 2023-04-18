package top.ddgotxdy.common.model.article.queryparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CategoryQueryParam {
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 是否删除
     */
    private Boolean isDelete;
}
