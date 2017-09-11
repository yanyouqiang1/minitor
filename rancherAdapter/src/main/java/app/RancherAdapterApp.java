package app;

import app.prometheus.PrometheusConfig;
import app.rancher.RancherConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Administrator on 2017/8/29.
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({RancherConfig.class, PrometheusConfig.class})
public class RancherAdapterApp {
    public static void main(String[] args) {
        SpringApplication.run(RancherAdapterApp.class, args);
    }
}
