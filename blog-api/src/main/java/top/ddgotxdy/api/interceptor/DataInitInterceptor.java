package top.ddgotxdy.api.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.ddgotxdy.common.scope.ContextScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ddgo
 * @description: 请求解析出部分需要的数据，进行初始化
 *
 * preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
 * postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView （这个博主就基本不怎么用了）；
 * afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
 */
@Component
@Slf4j
public class DataInitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("DataInitInterceptor 初始化");
        // 1.请求uri
        String uri = request.getRequestURI();
        ContextScope.setRequestUri(uri);
        // 2.请求方法
        String method = request.getMethod();
        ContextScope.setRequestMethod(method);
        // 3. ip地址
        String ip = this.getIpAddress(request);
        ContextScope.setIp(ip);
        log.info("uri [{}] method [{}] ip [{}]", uri, method, ip);
        // 4. trace id
        TraceContext traceContext = (TraceContext)request.getAttribute(TraceContext.class.getName());
        String traceId = traceContext.traceId();
        ContextScope.setTraceId(traceId);
        log.info("trace id [{}]", traceId);
        return true;
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
