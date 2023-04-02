package top.ddgotxdy.common.model;

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
    // 801 - 900 文件异常
    ;
    private final Integer code;
    private final String msg;
}
