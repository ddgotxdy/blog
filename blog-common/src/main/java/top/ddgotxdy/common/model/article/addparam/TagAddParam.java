package top.ddgotxdy.common.model.article.addparam;

import org.hibernate.validator.constraints.Length;

/**
 * @author: ddgo
 * @description: 标签添加参数
 */
public class TagAddParam {
    /**
     * 标签名称
     */
    @Length(min = 1, max = 10)
    private String tagName;
}
