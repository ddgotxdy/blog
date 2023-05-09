package top.ddgotxdy.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: ddgo
 * @description: 敏感词过滤切面
 */
@Aspect
@Component
public class SensitiveWordAspect {

    @Pointcut("@annotation(top.ddgotxdy.common.annotation.SensitiveWord)")
    public void sensitiveWordPoint(){
    }

    @Around("sensitiveWordPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object text = pjp.getThis();
        // 如果不是string类型，则不处理
        if (!(text instanceof String)) {
            return pjp.proceed();
        }
        // 否者替换敏感词
        String textString = (String) text;
//        SensitiveWordBs sensitiveWordBs = sensitiveWordService.getSensitiveWordBs();
//        textString = sensitiveWordBs.replace(textString);

        return pjp.proceed();
    }
}
