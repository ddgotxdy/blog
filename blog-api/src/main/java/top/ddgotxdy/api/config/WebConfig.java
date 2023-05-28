package top.ddgotxdy.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ddgotxdy.api.interceptor.DataInitInterceptor;

/**
 * @author: ddgo
 * @description: 拦截器配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     * @param registry 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(new DataInitInterceptor())
                .addPathPatterns("/**");
    }
}