package top.ddgotxdy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author: ddgo
 * @description:
 */
@Getter
@AllArgsConstructor
public enum OplogType {
    /**
     * 未知
     */
    UNKNOWN_OPLOG_TYPE(0),
    /**
     * 文章主体新增事件
     */
    ARTICLE_BODY_ADD(1),
    /**
     * 文章主体更新事件
     */
    ARTICLE_BODY_UPDATE(2),
    /**
     * 文章主体删除事件
     */
    ARTICLE_BODY_DELETE(3),
    /**
     * 文章主体恢复事件
     */
    ARTICLE_BODY_RECOVER(4),
    /**
     * 标签新增事件
     */
    TAG_ADD(5),
    /**
     * 标签修改事件
     */
    TAG_UPDATE(6),
    /**
     * 标签删除事件
     */
    TAG_DELETE(7),
    /**
     * 标签恢复事件
     */
    TAG_RECOVER(8),
    /**
     * 分类新增事件
     */
    CATEGORY_ADD(9),
    /**
     * 分类修改事件
     */
    CATEGORY_UPDATE(10),
    /**
     * 分类新增事件
     */
    CATEGORY_DELETE(11),
    /**
     * 分类恢复事件
     */
    CATEGORY_RECOVER(12),
    ;
    private final Integer code;

    public static OplogType of(Integer code) {
        for (OplogType oplogType : OplogType.values()) {
            if (Objects.equals(oplogType.getCode(), code)) {
                return oplogType;
            }
        }
        return UNKNOWN_OPLOG_TYPE;
    }
}
