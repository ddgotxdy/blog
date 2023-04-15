package top.ddgotxdy.common.model.article.queryparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description: 标签查询参数
 */
@Data
public class TagQueryParam {
    /**
     * 标签id
     */
    private Long tagId;
    /**
     * 标签名称
     */
    private String tagName;
    /**
     * 是否删除
     */
    private Boolean isDelete;
}
