package app.outerInterface;

import app.database.service.StrategyContainerService;
import app.database.service.StrategyTimePeriodService;
import app.feignclient.monitor.Monitor;
import app.feignclient.targetAdapter.Adapter;
import app.feignclient.targetAdapter.AdapterService;
import app.outerInterface.entity.observation.*;
import app.outerInterface.entity.reply.CommonReply;
import app.outerInterface.entity.reply.CommonReplyBuilder;
import app.outerInterface.entity.task.AutomaticList;
import app.outerInterface.entity.task.ManualList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
@RestController
public class RestImpl implements RestInter {
    @Autowired
    Adapter adapter;

    @Autowired
    Monitor monitor;

    @Override
    public RestServices getRestService() {
        List<AdapterService> adapterServices =  adapter.getServices();
        return RestServices.generate(adapterServices);
    }

    @Override
    public RestServiceResponse getRestServiceResponse(String serviceName) {
        int[] responses = monitor.getServiceRecentResponseTime(serviceName);
        return RestServiceResponse.generate(serviceName,responses);
    }

    @Autowired
    StrategyContainerService containerService;

    @Override
    public RestServiceContainer getRestServiceContainer(String serviceName) {
        return RestServiceContainer.generate(serviceName,containerService.getLatestContainers(serviceName));
    }

    @Override
    public RestServiceContainerCpu getRestServiceContainerCpu(String serviceName) {
        RestServiceContainerCpu serviceContainerCpu = new RestServiceContainerCpu(serviceName);
        List<AdapterService> services = adapter.getServices();
        AdapterService findService=null;
        for(AdapterService adapterService:services){
            if(adapterService.getName().equals(serviceName)){
                findService = adapterService;
            }
        }
        if(findService!=null){
            List<String> containerNames = findService.getContainerNames();
            for (String name:containerNames){
                RestServiceContainerCpu.ContainerCPU containerCPU = new RestServiceContainerCpu.ContainerCPU(name);
                containerCPU.setData(adapter.getCpuRateByContainerName(name));
                serviceContainerCpu.getContainerCpus().add(containerCPU);
            }

        }
        return serviceContainerCpu;
    }

    @Override
    public RestServiceContainerMemory getRestServiceContainerMemory(String serviceName) {
        RestServiceContainerMemory serviceContainerMemory = new RestServiceContainerMemory(serviceName);
        List<AdapterService> services = adapter.getServices();
        AdapterService findService=null;
        for(AdapterService adapterService:services){
            if(adapterService.getName().equals(serviceName)){
                findService = adapterService;
            }
        }
        if(findService!=null){
            List<String> containerNames = findService.getContainerNames();
            for (String name:containerNames){
                RestServiceContainerMemory.ContainerMemory containerMemory = new RestServiceContainerMemory.ContainerMemory(name);
                containerMemory.setData(adapter.getCpuRateByContainerName(name));
                serviceContainerMemory.getContainerMemorys().add(containerMemory);
            }

        }
        return serviceContainerMemory;
    }

    @Override
    public AutomaticList getAutomaticList() {
        return null;
    }

    @Override
    public ManualList getManualList() {
        return null;
    }

    @Autowired
    StrategyTimePeriodService timePeriodService;
    @Override
    public CommonReply updateStrategyTimePeriod(String serviceName, String peek, String thought) {
        timePeriodService.insertStrategy(serviceName,peek,thought);
        return CommonReplyBuilder.buildSuccessReply();
    }

    @Override
    public CommonReply updateStrategyVisitorLimit(String serviceName, long upper, long lower) {

        return null;
    }

    @Override
    public CommonReply updateStrategyVisitorAverage(String methodName, long upper, long lower) {
        return null;
    }

    @Override
    public CommonReply updateStrategyOverallSwitch(String strategyName, boolean sswitch) {
        return null;
    }

    @Override
    public CommonReply updateStrategyResponseTime(String methodName, long lower, long upper, long upperLimit) {
        return null;
    }
}
