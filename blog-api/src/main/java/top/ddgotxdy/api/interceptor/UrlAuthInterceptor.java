package top.ddgotxdy.api.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import top.ddgotxdy.common.constant.RedisPrefix;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.util.RedisCache;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: ddgo
 * @description: 接口权限校验
 */
@Slf4j
public class UrlAuthInterceptor implements HandlerInterceptor {
    @Resource
    private RedisCache redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("UrlAuthInterceptor 初始化");
        String uri = ContextScope.getRequestUri();
        Long userId = ContextScope.getUserId();
        String redisKey = RedisPrefix.LOGIN + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        List<String> uris = loginUser.getUris();
        // TODO 开发阶段不拦截
//        if (!uris.contains(uri)) {
//            throw new BlogException(HttpStatus.FORBIDDEN.value(), "权限不足");
//        }
        return true;
    }
}
