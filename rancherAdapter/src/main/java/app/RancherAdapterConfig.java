package app;

import app.prometheus.PrometheusConfig;
import app.prometheus.PrometheusImpl;
import app.rancher.ContainerInter;
import app.rancher.RancherConfig;
import app.rancher.RancherPresetValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/7/1 0001.
 */
@Configuration
public class RancherAdapterConfig {
    @Autowired
    RancherConfig rancherConfig;

    @Bean
    public RancherPresetValue rancherPresetValue(){
        return new RancherPresetValue(rancherConfig);
    }


    @Autowired
    PrometheusConfig prometheusConfig;
    @Bean
    public ContainerInter getContainerImpl(){
        return new PrometheusImpl(prometheusConfig);
    }
}
