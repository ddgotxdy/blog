package top.ddgotxdy.api.scope;

import com.github.phantomthief.scope.ScopeKey;

/**
 * @author: ddgo
 * @description: 线程上下文对象
 */
public class ContextScope {
    private ContextScope() { }

    /**
     * 用户的id
     */
    private static final ScopeKey<Long> USER_ID_SCOPE = ScopeKey.withDefaultValue(0L);
    /**
     * 请求的URI地址
     */
    private static final ScopeKey<String> REQUEST_URI_SCOPE = ScopeKey.withDefaultValue("");
    /**
     * 请求方法
     */
    private static final ScopeKey<String> REQUEST_METHOD_SCOPE = ScopeKey.withDefaultValue("");
    /**
     * ip地址
     */
    private static final ScopeKey<String> IP_SCOPE = ScopeKey.withDefaultValue("");

    /**
     * user id 赋值
     * @param id user id
     */
    public static void setUserId(Long id) {
        USER_ID_SCOPE.set(id);
    }

    /**
     * 获取 user id
     * @return user id
     */
    public static Long getUserId() {
        return USER_ID_SCOPE.get();
    }

    /**
     * 请求地址
     * @param uri uri
     */
    public static void setRequestUri(String uri) {
        REQUEST_URI_SCOPE.set(uri);
    }

    /**
     * 获取请求地址
     * @return uri
     */
    public static String getRequestUri() {
        return REQUEST_URI_SCOPE.get();
    }

    /**
     * 请求方法
     * @param method 请求方法
     */
    public static void setRequestMethod(String method) {
        REQUEST_METHOD_SCOPE.set(method);
    }

    /**
     * 获取请求方法
     * @return method
     */
    public static String getRequestMethod() {
        return REQUEST_METHOD_SCOPE.get();
    }

    /**
     * 设置ip地址
     * @param ip 请求方法
     */
    public static void setIp(String ip) {
        IP_SCOPE.set(ip);
    }

    /**
     * 获取ip地址
     * @return ip
     */
    public static String getIp() {
        return IP_SCOPE.get();
    }

}
