package app;

import app.containerstate.prometheus.PrometheusConfig;
import app.schedule.rancherImpl.RancherConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
@SpringBootApplication
@EnableConfigurationProperties({RancherConfig.class, PrometheusConfig.class})
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
