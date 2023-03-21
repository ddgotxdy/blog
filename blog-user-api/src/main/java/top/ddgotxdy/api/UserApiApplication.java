package top.ddgotxdy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: ddgo
 * @description: 启动类
 */
@SpringBootApplication
public class UserApiApplication {
    public static void main(String[] args) {
//        SpringApplication.run(UserApiApplication.class, args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserApiApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty(" current.env");
        System.err.println("user name :"+userName);
    }
}
