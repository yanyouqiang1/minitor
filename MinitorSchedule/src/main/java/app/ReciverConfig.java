package app;

import app.handle.Commander;
import app.handle.MinitorConfig;
import app.schedule.rancherImpl.RancherConfig;
import app.schedule.rancherImpl.RancherOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
@Configuration
public class ReciverConfig {
    @Autowired
    MinitorConfig minitorConfig;
    @Bean
    public Commander get() {
        return new Commander(minitorConfig);
    }


    @Autowired
    RancherConfig rancherConfig;

    @Bean
    public RancherOS rancherOS(){
        return new RancherOS(rancherConfig);
    }


}
