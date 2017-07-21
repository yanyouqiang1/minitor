package app;

import app.schedule.rancher.RancherConfig;
import app.schedule.rancher.RancherOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
@Configuration
public class AppConfig {
    @Autowired
    RancherConfig rancherConfig;

    @Bean
    public RancherOS getRancherOS(){
        return new RancherOS(rancherConfig);
    }
}
