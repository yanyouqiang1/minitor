package app.webInterface.targetAdapter;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface Adapter {
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
