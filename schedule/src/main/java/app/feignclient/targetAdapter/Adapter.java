package app.feignclient.targetAdapter;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */
@FeignClient("rancherAdapter")
public interface Adapter {
    //获取所有服务
    @RequestMapping("/acquiring/services")
    public List<AdapterService> getServices();

    //获得某个容器CPU信息
    @RequestMapping("/acquiring/container/cpu")
    public AdapterResultData getCpuRateByContainerName(String containerName);

    //获得某个容器memory信息
    @RequestMapping("/acquiring/container/memory")
    public AdapterResultData getMemoryByContainerName(String containerName);

    //简化负载列表
    @RequestMapping("/function/simplifyList")
    public List<AdapterService> simplifyOverloadList(List<AdapterService> serviceList);
    //触发
    //增加
    @RequestMapping("/trigger/upService")
    public boolean upService(String serviceName);
    //减少
    @RequestMapping("/trigger/downService")
    public boolean downService(String serviceName);


}
