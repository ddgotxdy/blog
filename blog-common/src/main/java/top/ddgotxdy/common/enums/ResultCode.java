package top.ddgotxdy.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: ddgo
 * @description: 状态值枚举定义
 */
@Getter
@AllArgsConstructor
public enum ResultCode {
    // 601 - 700 自定义异常
    SYSTEM_ERROR(601, "系统其它异常"),
    // 701 - 800 文章异常
    ARTICLE_ADD_ERROR(701, "文章新增失败"),
    ARTICLE_DELETE_ERROR(702, "文章删除失败"),
    ARTICLE_UPDATE_ERROR(703, "文章修改失败"),
    ARTICLE_QUERY_ERROR(704, "文章查询失败"),
    TAG_ADD_ERROR(705, "标签新增失败"),
    TAG_DELETE_ERROR(706, "标签删除失败"),
    TAG_UPDATE_ERROR(707, "标签修改失败"),
    TAG_QUERY_ERROR(708, "标签查询失败"),
    TAG_RECOVERY_ERROR(709, "标签恢复失败"),
    CATEGORY_ADD_ERROR(710, "分类新增失败"),
    CATEGORY_DELETE_ERROR(711, "分类删除失败"),
    CATEGORY_UPDATE_ERROR(712, "分类修改失败"),
    CATEGORY_QUERY_ERROR(713, "分类查询失败"),
    CATEGORY_RECOVERY_ERROR(714, "分类恢复失败"),
    // 801 - 900 文件异常
    IMAGE_ADD_ERROR(801, "图片新增失败"),
    IMAGE_UPDATE_ERROR(802, "图片修改失败"),
    IMAGE_QUERY_ERROR(803, "图片查询失败"),
    ;
    private final Integer code;
    private final String msg;
}
