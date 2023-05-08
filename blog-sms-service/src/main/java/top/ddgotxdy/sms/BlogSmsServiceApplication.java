package top.ddgotxdy.sms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: ddgo
 * @description:
 */
@SpringBootApplication(scanBasePackages = "top.ddgotxdy.*")
@EnableDiscoveryClient
@MapperScan("top.ddgotxdy.dal.mapper")
public class BlogSmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogSmsServiceApplication.class, args);
    }
}
