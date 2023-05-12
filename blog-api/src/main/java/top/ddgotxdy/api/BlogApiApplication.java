package top.ddgotxdy.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: ddgo
 * @description: 启动类
 */
@SpringBootApplication(scanBasePackages = {
        "top.ddgotxdy.*"
})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "top.ddgotxdy.*")
@MapperScan("top.ddgotxdy.dal.mapper")
public class BlogApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }
}
