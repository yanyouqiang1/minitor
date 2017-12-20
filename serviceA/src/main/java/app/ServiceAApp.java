package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import javax.swing.*;

/**
 * Created by Administrator on 2017/9/12.
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableConfigurationProperties({ServiceConfig.class})
public class ServiceAApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAApp.class, args);
    }

}
