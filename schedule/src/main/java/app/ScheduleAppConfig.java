//package app;
//
//import app.containerstate.prometheus.PrometheusConfig;
//import app.containerstate.prometheus.PrometheusImpl;
//import app.schedule.rancherImpl.ContainerInter;
//import app.schedule.rancherImpl.RancherConfig;
//import app.schedule.rancherImpl.RancherPresetValue;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by Administrator on 2017/7/1 0001.
// */
//@Configuration
//public class ScheduleAppConfig {
//    @Autowired
//    RancherConfig rancherConfig;
//
//    @Bean
//    public RancherPresetValue rancherPresetValue(){
//        return new RancherPresetValue(rancherConfig);
//    }
//
//
//    @Autowired
//    PrometheusConfig prometheusConfig;
//    @Bean
//    public ContainerInter getContainerImpl(){
//        return new PrometheusImpl(prometheusConfig);
//    }
//}
