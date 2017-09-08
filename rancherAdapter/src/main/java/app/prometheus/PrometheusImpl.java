package app.prometheus;


import app.rancher.ContainerInter;
import app.rancher.entity.ResultData;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/28.
 */
@Component
public class PrometheusImpl implements ContainerInter {

    PrometheusConfig config;
    public  PrometheusImpl(PrometheusConfig config){
        this.config = config;
    }

    @Override
    public ResultData getCpuRateByContainerName(String containerName) {
        long currentTime = System.currentTimeMillis()/1000;
        ResultData resultData=null;
        try{
            resultData = PrometheusHttpApi.getCpuData(config.getAddress(),containerName,String.valueOf(currentTime-90),String.valueOf(currentTime),"10");
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultData;
    }

    @Override
    public ResultData getMemoryByContainerName(String containerName) {
        long currentTime = System.currentTimeMillis()/1000;
        ResultData resultData=null;
        try{
            resultData = PrometheusHttpApi.getMemoryData(config.getAddress(),containerName,String.valueOf(currentTime-90),String.valueOf(currentTime),"10");
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultData;
    }
}
