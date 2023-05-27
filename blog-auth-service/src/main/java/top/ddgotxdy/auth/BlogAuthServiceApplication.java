package top.ddgotxdy.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: ddgo
 * @description:
 */
@SpringBootApplication(
        scanBasePackages = "top.ddgotxdy.*"
)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "top.ddgotxdy.common.client")
@MapperScan("top.ddgotxdy.dal.mapper")
public class BlogAuthServiceApplication {
        public static void main(String[] args) {
                SpringApplication.run(BlogAuthServiceApplication.class, args);
        }
}
