package app.webInterface;

import app.database.domain.Monitor_services;
import app.database.service.ScheduleService;
import app.database.service.ServiceService;
import app.schedule.SchedulePresetValue;
import app.schedule.rancherImpl.RancherPresetValue;
import app.schedule.rancherImpl.RancherService;
import app.schedule.rancherImpl.RancherStack;
import app.webInterface.entity.observation.*;
import app.webInterface.entity.setting.CommonReply;
import app.webInterface.entity.setting.CommonReplyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */
@Component
public class RestImpl implements RestInter {
    @Autowired
    ServiceService serviceService;

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    RancherStack rancherStack;

    @Autowired
    RancherPresetValue rancherPresetValue;

    @Autowired
    SchedulePresetValue schedulePresetValue;

    @Override
    public RestServices getRestService() {
        List<List<Monitor_services>> groupServiceList = serviceService.getTop90GroupService();
        return RestServices.generate(groupServiceList);
    }


    @Override
    public RestServiceTopology getServiceTopology() {
        return RestServiceTopology.generate(rancherStack.getRancherServices());
    }

    @Override
    public RestServiceResponse getRestServiceResponse(String serviceName) {
        return RestServiceResponse.generate(serviceService.getTop90ByServiceName(serviceName));
    }

    @Override
    public RestServiceContainer getRestServiceContainer(String serviceName) {
        return RestServiceContainer.generate(scheduleService.getScheduleByServiceName(serviceName));
    }



    @Override
    public RestServiceContainerCpu getRestServiceContainerCpu(String serviceName) {
        RestServiceContainerCpu serviceContainerCpu = new RestServiceContainerCpu();
        RancherService rancherService = rancherStack.getServiceByName(serviceName);
        if (rancherService != null) {
            serviceContainerCpu.setServiceName(rancherService.getName());
            for(String containerName:rancherService.getContainerNames()){
                RestServiceContainerCpu.ContainerCPU containerCPU = new RestServiceContainerCpu.ContainerCPU(containerName);
                containerCPU.setData(rancherStack.getContainerCpuStatusByName(containerName));
                serviceContainerCpu.getContainerCpus().add(containerCPU);
            }
        }
        return serviceContainerCpu;
    }

    @Override
    public RestServiceContainerMemory getRestServiceContainerMemory(String serviceName) {
        RestServiceContainerMemory serviceContainerMemory = new RestServiceContainerMemory();
        RancherService rancherService = rancherStack.getServiceByName(serviceName);
        if(rancherService!=null){
            serviceContainerMemory.setServiceName(rancherService.getName());
            for(String containerName:rancherService.getContainerNames()){
                RestServiceContainerMemory.ContainerMemory containerMemory = new RestServiceContainerMemory.ContainerMemory(containerName);
                containerMemory.setData(rancherStack.getContainerMemoryStatusByName(containerName));
                serviceContainerMemory.getContainerMemorys().add(containerMemory);
            }
        }
        return serviceContainerMemory;
    }


    @Override
    public CommonReply setWebhookUp(String serviceName, String upUrl) {
        if(rancherPresetValue.setServicUpUrl(serviceName,upUrl)){
            return CommonReplyBuilder.buildSuccessReply();
        }
        return CommonReplyBuilder.buildErrorReply("设置失败,请检查你的服务名");
    }

    @Override
    public CommonReply setWebhookDown(String serviceName, String downUrl) {
        if(rancherPresetValue.setServiceDownUrl(serviceName,downUrl)){
            return CommonReplyBuilder.buildSuccessReply();
        }
        return CommonReplyBuilder.buildErrorReply("设置失败,请检查你的服务名");
    }

    @Override
    public CommonReply setMethodThresholdUpper(Long methodID, int upperThreshold) {
        schedulePresetValue.setMethodUpperThreshold(methodID,upperThreshold);
        return CommonReplyBuilder.buildSuccessReply();
    }

    @Override
    public CommonReply setMethodThresholdLower(Long methodID, int lowerThreshold) {
        schedulePresetValue.setMethodLowerThreshold(methodID,lowerThreshold);
        return CommonReplyBuilder.buildSuccessReply();
    }
}
