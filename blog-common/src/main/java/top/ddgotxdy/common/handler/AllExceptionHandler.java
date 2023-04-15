package top.ddgotxdy.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.ddgotxdy.common.model.ResultCode;
import top.ddgotxdy.common.model.ResultView;

/**
 * @author: ddgo
 * @description: 全局异常拦截
 */
@Slf4j
@RestControllerAdvice
public class AllExceptionHandler {
    /**
     * 进行异常处理，处理Exception.class的异常
     * @param ex 异常
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public ResultView doException(Exception ex) {
        ex.printStackTrace();
        return ResultView.fail(ResultCode.SYSTEM_ERROR.getMsg());
    }

}
