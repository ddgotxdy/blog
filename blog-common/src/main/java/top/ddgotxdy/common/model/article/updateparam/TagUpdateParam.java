package top.ddgotxdy.common.model.article.updateparam;

import lombok.Data;

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
    private String tagName;
}
