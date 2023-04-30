package top.ddgotxdy.api.filter;

import com.github.phantomthief.scope.Scope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ddgo
 * @description:
 */
@Order(0)
@Component
@Slf4j
public class ScopeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 开启Scope
        Scope.beginScope();
        try {
            filterChain.doFilter(request, response);
        } finally {
            // 关闭Scope
            Scope.endScope();
        }
    }
}
