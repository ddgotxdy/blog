package top.ddgotxdy.api.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: ddgo
 * @description: swagger文档配置
 * EnableSwagger2 开启自动配置
 * 地址/doc.html knife4j
 * 地址/swagger-ui.html swagger
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    @Autowired
    private Environment environment;

    /**
     * 配置docket
     * @return Docket
     */
    @Bean
    public Docket docket() {
        // 检测是否为dev和test环境
        Profiles profiles = Profiles.of("dev");
        boolean isEnable = environment.acceptsProfiles(profiles);

        // 全局参数 token
        Parameter parameter = new ParameterBuilder()
                .name("token")
                .description("用户令牌")
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                .required(true)
                .build();
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameter);

        return new Docket(DocumentationType.SWAGGER_2)
                // 选择是否开启swagger
                .enable(isEnable)
                .apiInfo(apiInfo())
                // 全局参数 TODO 开发登录模块再设置
//                .globalOperationParameters(parameters)
                // 忽略请求参数的类型
//                .ignoredParameterTypes(Integer.class, Long.class)
                // 组名
                .groupName("ALL API")
                .select()
                // 选择api显示的范围 any,none,basePackage等里面
                .apis(RequestHandlerSelectors.basePackage("top.ddgotxdy.api.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("ddgo", "ddgotxdy.top", "1812805089@qq.com");
        return new ApiInfo(
                "博客API",
                "博客swagger生成文档",
                "v1.0.0",
                "",
                contact,
                "",
                "",
                new ArrayList<>());
    }

    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}
