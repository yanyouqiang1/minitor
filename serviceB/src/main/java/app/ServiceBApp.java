package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by Administrator on 2017/9/12.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceBApp {
    public static void main(String[] args){
        SpringApplication.run(ServiceBApp.class,args);
    }

}
