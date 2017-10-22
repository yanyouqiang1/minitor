package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableEurekaClient
public class ScheduleApp {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApp.class, args);
    }
}
