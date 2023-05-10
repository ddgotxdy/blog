package top.ddgotxdy.common.model.article.deleteparam;

import lombok.Data;

import java.util.List;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class ArticleBodyDeleteParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 文章id列表
     */
    private List<Long> articleIds;
}
