package app.webInterface;

import app.database.service.StrategyContainerService;
import app.feignclient.monitor.Monitor;
import app.feignclient.targetAdapter.Adapter;
import app.feignclient.targetAdapter.AdapterService;
import app.webInterface.entity.observation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
@Component
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
}
