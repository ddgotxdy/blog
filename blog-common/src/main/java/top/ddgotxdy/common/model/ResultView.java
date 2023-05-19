package top.ddgotxdy.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.util.ResultViewCheckUtil;

/**
 * @author: ddgo
 * @description: 同意返回给前端的类封装
 */
@ApiModel("统一返回值")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultView<T> {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("提示信息")
    private String msg;

    @ApiModelProperty("返回数据")
    private T data;

    @ApiModelProperty("链路跟踪id")
    private String traceId;

    public ResultView(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.traceId = ContextScope.getTraceId();
    }

    public ResultView(Integer code) {
        this.code = code;
        this.traceId = ContextScope.getTraceId();
    }

    public ResultView(Integer code, T data) {
        this.code = code;
        this.data = data;
        this.traceId = ContextScope.getTraceId();
    }

    public ResultView(Integer code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.traceId = ContextScope.getTraceId();
    }

    public T checkAndGetData() {
        // 先去校验是否成功
        ResultViewCheckUtil.checkResultView(this);
        // 再返回data
        return this.getData();
    }

    public static <T> ResultView<T> success(String message, T data) {
        return new ResultView<>(ResultCode.OK.getCode(), message, data);
    }

    public static <T> ResultView<T> success(T data) {
        return new ResultView<>(ResultCode.OK.getCode(), data);
    }

    public static <T> ResultView<T> success() {
        return new ResultView<>(ResultCode.OK.getCode());
    }

    public static <T> ResultView<T> fail(String message) {
        return new ResultView<>(ResultCode.ERROR.getCode(), message);
    }

    public static <T> ResultView<T> fail(Integer code, String message) {
        return new ResultView<>(code, message);
    }
}