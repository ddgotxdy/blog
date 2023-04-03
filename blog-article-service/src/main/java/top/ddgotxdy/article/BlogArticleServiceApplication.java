package top.ddgotxdy.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: ddgo
 * @description: 博客文章服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("top.ddgotxdy.dal.mapper")
public class BlogArticleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogArticleServiceApplication.class, args);
    }
}
