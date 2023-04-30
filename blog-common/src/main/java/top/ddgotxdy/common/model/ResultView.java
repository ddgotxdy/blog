package top.ddgotxdy.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

/**
 * @author: ddgo
 * @description: 同意返回给前端的类封装
 */
@ApiModel("统一返回值")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultView<T> {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("提示信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;

    @ApiModelProperty
    private String traceId;

    public ResultView(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.traceId = MDC.get("");
    }

    public ResultView(Integer code, T data) {
        this.code = code;
        this.data = data;
        this.traceId = MDC.get("TRACE_ID");
    }

    public ResultView(Integer code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.traceId = MDC.get("TRACE_ID");
    }

    public static <T> ResultView<T> success(String message, T data) {
        return new ResultView<>(200, message, data);
    }

    public static <T> ResultView<T> success(T data) {
        return new ResultView<>(200, data);
    }

    public static <T> ResultView<T> fail(String message) {
        return new ResultView<>(500, message);
    }

}