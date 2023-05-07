package top.ddgotxdy.file.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: ddgo
 * @description: 文章事件
 */
@Getter
@AllArgsConstructor
public enum FileEvent {
    /**
     * 图片新增事件
     */
    IMAGE_ADD(1),
    /**
     * 图片更新事件
     */
    IMAGE_UPDATE(2),
    /**
     * 图片删除事件
     */
    IMAGE_DELETE(3),
    /**
     * 图片恢复事件
     */
    IMAGE_RECOVER(4),
    ;
    private final int code;
}
