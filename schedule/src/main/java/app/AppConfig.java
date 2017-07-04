package app;

import app.manage.RancherConfig;
import app.manage.RancherOS;
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