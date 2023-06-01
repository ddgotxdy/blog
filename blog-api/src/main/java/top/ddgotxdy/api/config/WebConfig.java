package top.ddgotxdy.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.ddgotxdy.api.interceptor.DataInitInterceptor;
import top.ddgotxdy.api.interceptor.UrlAuthInterceptor;

import javax.annotation.Resource;

/**
 * @author: ddgo
 * @description: 拦截器配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private UrlAuthInterceptor urlAuthInterceptor;
    @Resource
    private DataInitInterceptor dataInitInterceptor;

    /**
     * 添加拦截器
     * @param registry 拦截器注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(dataInitInterceptor)
                .addPathPatterns("/**");
        registry
                .addInterceptor(urlAuthInterceptor)
                .addPathPatterns("/**");
    }
}