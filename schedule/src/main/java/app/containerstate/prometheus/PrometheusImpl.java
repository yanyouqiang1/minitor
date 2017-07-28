package app.containerstate.prometheus;

import app.containerstate.ContainerInter;
import app.containerstate.entity.ConsultData;
import app.global.HttpRequest;

/**
 * Created by Administrator on 2017/7/28.
 */
public class PrometheusImpl implements ContainerInter {

    PrometheusConfig config;
    public  PrometheusImpl(PrometheusConfig config){
        this.config = config;
    }

    @Override
    public ConsultData getCpuRateByContainerName(String containerName) {
        long currentTime = System.currentTimeMillis()/1000;
        String result = PrometheusHttpApi.urlSplicer(config.getAddress(),containerName,String.valueOf(currentTime-90),String.valueOf(currentTime),"10");
        return null;
    }

    @Override
    public ConsultData getMemoryByContainerName(String containerName) {
        return null;
    }
}
