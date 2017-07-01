package app;

import app.handle.Commander;
import app.handle.MinitorConfig;
import app.handle.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import app.schedule.*;

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
