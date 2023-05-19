package top.ddgotxdy.common.util;

import top.ddgotxdy.common.enums.ResultCode;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.ResultView;

import java.util.Objects;
import java.util.Optional;

/**
 * @author: ddgo
 * @description: 校验client调用是否返回成功
 */
public class ResultViewCheckUtil {
    public static void checkResultView(ResultView resultView) {
        // 为空，则返回异常
        if (Objects.isNull(resultView)) {
            throw new BlogException(ResultCode.SYSTEM_ERROR);
        }
        // code 不为成功
        Integer code = resultView.getCode();
        String msg = Optional.ofNullable(resultView.getMsg()).orElse("error");
        if (Objects.isNull(code) || !code.equals(ResultCode.OK.getCode())) {
            throw new BlogException(code, msg);
        }
    }
}
