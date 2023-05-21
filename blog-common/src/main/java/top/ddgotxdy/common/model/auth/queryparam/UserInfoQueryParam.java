package top.ddgotxdy.common.model.auth.queryparam;

import lombok.Data;

/**
 * @author: ddgo
 * @description:
 */
@Data
public class UserInfoQueryParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 是否删除
     */
    private Boolean isDelete;
    /**
     * 用户名全等于
     */
    private String usernameEq;
    /**
     * 邮箱全等于
     */
    private String emailEq;
}
