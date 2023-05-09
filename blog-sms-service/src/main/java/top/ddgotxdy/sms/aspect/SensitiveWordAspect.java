package top.ddgotxdy.sms.aspect;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import top.ddgotxdy.common.annotation.SensitiveWordProperty;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author: ddgo
 * @description: 敏感词过滤切面
 */
@Slf4j
@Aspect
@Component
public class SensitiveWordAspect {

    @Resource
    private SensitiveWordBs sensitiveWordBs;

    @Pointcut("@annotation(top.ddgotxdy.common.annotation.SensitiveWord)")
    public void sensitiveWordPoint(){
    }

    @Around("sensitiveWordPoint()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object target = pjp.getTarget();
        Field[] declaredFields = target.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 判断是否被注解标注
            SensitiveWordProperty sensitiveWordProperty = declaredField.getAnnotation(SensitiveWordProperty.class);
            if (Objects.isNull(sensitiveWordProperty)) {
                continue;
            }
            // 判断对应的类型，为string才处理
            Class<?> type = declaredField.getType();
            if(!type.isAssignableFrom(String.class)) {
                continue;
            }
            declaredField.setAccessible(true);
            String fieldValue = (String) declaredField.get(target);
            log.info("【敏感词过滤之前】[{}]", fieldValue);
            // 敏感词过滤
            fieldValue = sensitiveWordBs.replace(fieldValue);
            log.info("【敏感词过滤之后】[{}]", fieldValue);
            declaredField.set(target, fieldValue);
        }
        return pjp.proceed();
    }
}
