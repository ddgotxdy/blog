package top.ddgotxdy.common.model.article.recoverparam;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class CategoryRecoverParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 需要恢复的标签列表
     */
    private List<Long> categoryIds;
}
