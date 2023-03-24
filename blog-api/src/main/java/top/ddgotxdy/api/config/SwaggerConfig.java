package top.ddgotxdy.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ddgo
 * @description: swagger文档配置
 * EnableSwagger2 开启自动配置
 */
@Configuration
@EnableSwagger2
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
        Profiles profiles = Profiles.of("dev","test");
        boolean isEnable = environment.acceptsProfiles(profiles);

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
                .apiInfo(apiInfo())
                .globalOperationParameters(parameters)
                // 忽略请求参数的类型
//                .ignoredParameterTypes(Integer.class, Long.class)
                // 选择api显示的范围 any,none,basePackage等里面
                // 选择是否开启swagger
//                .enable(isEnable)
                // 组名
                .groupName("用户")
                .select()
                .apis(RequestHandlerSelectors.basePackage("top.ddgotxdy.api.controller"))
                // 筛选请求
                .paths(PathSelectors.ant("/user"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("ddgotxdy", "ddgotxdy.top", "1812805089@qq.com");
        return new ApiInfo(
                "swagger学习接口文档",
                "这是学习swagger生成的文档",
                "v1.0.0",
                "localhost:8080",
                contact,
                "",
                "",
                new ArrayList<>());
    }
}
