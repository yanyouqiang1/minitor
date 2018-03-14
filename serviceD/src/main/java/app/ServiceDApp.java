package app;

import app.aspect.RabbitChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * Created by Administrator on 2017/9/12.
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableConfigurationProperties({ServiceConfig.class})
@EnableBinding(RabbitChannel.class)
public class ServiceDApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDApp.class, args);
    }
}
