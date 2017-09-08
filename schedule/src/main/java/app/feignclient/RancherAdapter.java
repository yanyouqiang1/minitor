package app.feignclient;

import app.OtherSchedule.AbstractService;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface RancherAdapter {
    //获取所有服务
    public List<AbstractionService> getServices();

    //获得某个容器CPU信息
    public AbstractionResultData getCpuRateByContainerName(String containerName);

    //获得某个容器memory信息
    public AbstractionResultData getMemoryByContainerName(String containerName);

    //触发
    //增加
    public boolean upService(String serviceName);
    //减少
    public boolean downService(String serviceName);

}
