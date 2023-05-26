package top.ddgotxdy.api.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import top.ddgotxdy.common.constant.RedisPrefix;
import top.ddgotxdy.common.exception.BlogException;
import top.ddgotxdy.common.model.LoginUser;
import top.ddgotxdy.common.scope.ContextScope;
import top.ddgotxdy.common.util.JwtUtil;
import top.ddgotxdy.common.util.RedisCache;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import static top.ddgotxdy.common.enums.ResultCode.LOGIN_ERROR;
import static top.ddgotxdy.common.enums.ResultCode.LOGIN_EXPIRE_ERROR;

/**
 * @author ddgo
 * @description: 过滤处理token
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;
    @Resource(name = "handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    public static final Long REFRESH_TIME = 15L * 60 * 1000;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        String userId;
        Claims claims;
        try {
            claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (ExpiredJwtException expiredJwtException) {
            // 过期了，得重新登录
            BlogException exception = new BlogException(LOGIN_EXPIRE_ERROR);
            resolver.resolveException(request, response, null, exception);
            return;
        } catch (Exception e) {
            BlogException exception = new BlogException(LOGIN_ERROR.getCode(), "token非法");
            resolver.resolveException(request, response, null, exception);
            return;
        }
        // 从redis中获取用户信息
        log.info("userId [{}]", userId);
        ContextScope.setUserId(Long.valueOf(userId));
        String redisKey = RedisPrefix.LOGIN + userId;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)) {
            BlogException exception = new BlogException(LOGIN_ERROR.getCode(), "用户未登录");
            resolver.resolveException(request, response, null, exception);
            return;
        }
        // 如果时间小于15分钟，则重新生成token
        Date expiration = claims.getExpiration();
        long time = expiration.getTime();
        long nowTime = System.currentTimeMillis();
        if (time - nowTime <= REFRESH_TIME) {
            String jwt = JwtUtil.createJWT(userId);
            response.addHeader("token", jwt);
        }

        // 存入SecurityContextHolder
        // 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}
