package com.ddgotxdy.aspect;

import com.alibaba.fastjson.JSON;
import com.ddgotxdy.annotation.SysLog;
import com.ddgotxdy.util.HttpContextUtil;
import com.ddgotxdy.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author: ddgo
 * @description: 系统操作日志切面
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    @Pointcut("@annotation(com.ddgotxdy.annotation.SysLog)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        recordLog(point, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog logAnnotation = method.getAnnotation(SysLog.class);
        log.info("=====================log start================================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operation());

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");

        // 请求的参数
//        Object[] args = joinPoint.getArgs();
//        String params = JSON.toJSONString(args[0]);
//        log.info("params:{}",params);

        // 获取request 设置IP地址
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        log.info("ip:{}", IpUtil.getIpAddr(request));


        log.info("execute time : {} ms",time);
        log.info("=====================log end================================");
    }

}
