package app.containerstate.prometheus;

import app.containerstate.ContanerInter;
import app.containerstate.entity.ConsultData;

/**
 * Created by Administrator on 2017/7/28.
 */
public class PrometheusImpl implements ContanerInter {

    PrometheusConfig config;
    public  PrometheusImpl(PrometheusConfig config){
        this.config = config;
    }

    @Override
    public ConsultData getCpuRateByContainerName(String containerName) {
        String url = PrometheusHttpApi.
        return null;
    }

    @Override
    public ConsultData getMemoryByContainerName(String containerName) {
        return null;
    }
}
