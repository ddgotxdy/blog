package top.ddgotxdy.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author: ddgo
 * @description: 自定义异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BlogException extends RuntimeException {
    private Integer code;
    public BlogException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
