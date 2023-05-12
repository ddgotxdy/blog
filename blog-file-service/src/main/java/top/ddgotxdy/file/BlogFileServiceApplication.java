package top.ddgotxdy.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: ddgo
 * @description:
 */
@SpringBootApplication(
        scanBasePackages = "top.ddgotxdy.*",
        exclude = {
                SecurityAutoConfiguration.class
        }
)
@EnableDiscoveryClient
@MapperScan("top.ddgotxdy.dal.mapper")
public class BlogFileServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogFileServiceApplication.class, args);
    }
}
