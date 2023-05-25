package top.ddgotxdy.common.enums.article;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: ddgo
 * @description:
 */
@Getter
@AllArgsConstructor
public enum ArticleStatus {
    /**
     * 未知类型
     */
    ARTICLE_UNKNOWN(0, "未知类型"),
    /**
     * 公开
     */
    ARTICLE_ALL(1, "公开"),
    /**
     * 私密，管理员可见
     */
    AUDIT_ADMIN(2, "私密"),
    ;
    private final Integer code;
    private final String msg;

    @JsonValue
    public int getCode() {
        return this.code;
    }

    @JsonCreator
    public static ArticleStatus of(int code) {
        for (ArticleStatus articleStatus : ArticleStatus.values()) {
            if (articleStatus.getCode() == code) {
                return articleStatus;
            }
        }
        return ArticleStatus.ARTICLE_UNKNOWN;
    }
}
