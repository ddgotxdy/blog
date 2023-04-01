package top.ddgotxdy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: ddgo
 * @description: 博客网关启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BlogGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogGatewayApplication.class, args);
    }
}
