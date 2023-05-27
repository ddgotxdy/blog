package top.ddgotxdy.actuator;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: ddgo
 * @description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class BlogActuatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogActuatorApplication.class, args);
    }
}
