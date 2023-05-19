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
    // 正确返回
    OK(200, "ok"),
    // 异常返回
    ERROR(500, "error"),
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
    ARTICLE_RECOVERY_ERROR(715, "文章恢复失败"),
    // 801 - 900 文件异常
    IMAGE_ADD_ERROR(801, "图片新增失败"),
    IMAGE_UPDATE_ERROR(802, "图片修改失败"),
    IMAGE_QUERY_ERROR(803, "图片查询失败"),
    // 901 - 1000 消息异常
    SENSITIVE_ADD_ERROR(901, "敏感词新增失败"),
    SENSITIVE_DELETE_ERROR(902, "敏感词删除失败"),
    SENSITIVE_UPDATE_ERROR(903, "敏感词修改失败"),
    SENSITIVE_QUERY_ERROR(904, "敏感词查询失败"),
    SENSITIVE_RECOVERY_ERROR(905, "敏感词恢复失败"),
    MESSAGE_ADD_ERROR(906, "留言添加失败"),
    MESSAGE_UPDATE_ERROR(906, "留言更新失败"),
    SEND_MAIL_ERROR(907, "发送邮件失败"),
    // 1001 - 1100 授权认证异常
    LOGIN_ERROR(1001, "账户或密码错误"),
    ROLE_ERROR(1002, "角色异常"),
    CAPTCHA_EXPIRE_ERROR(1003, "验证码过期"),
    REGISTER_ERROR(1004, "注册失败"),
    PASSWORD_NOT_EQUAL(1005, "密码不相等"),
    CAPTCHA_ERROR(1006, "验证码错误"),
    ;
    private final Integer code;
    private final String msg;
}
