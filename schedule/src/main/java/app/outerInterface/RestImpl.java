package app.outerInterface;

import app.Schedule.strategy.overall.TimeWindow;
import app.Schedule.strategy.single.service.ServiceTimePeriod;
import app.Schedule.strategy.single.service.ServiceVisitorLimit;
import app.database.domain.Strategy_timePeriod;
import app.database.domain.Strategy_timeWindow;
import app.database.domain.Strategy_visitorAverage;
import app.database.domain.Strategy_visitorLimit;
import app.database.service.*;
import app.feignclient.monitor.Monitor;
import app.feignclient.targetAdapter.Adapter;
import app.feignclient.targetAdapter.AdapterService;
import app.feignclient.targetAdapter.SimplifyService;
import app.outerInterface.entity.observation.*;
import app.outerInterface.entity.reply.CommonReply;
import app.outerInterface.entity.reply.CommonReplyBuilder;
import app.outerInterface.entity.strategy.Overview;
import app.outerInterface.entity.task.AutomaticList;
import app.outerInterface.entity.task.ManualList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
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
    public RestServiceResponse getRestServiceResponse(@PathVariable(name = "serviceName",required = true)String serviceName) {
        int[] responses = monitor.getServiceRecentResponseTime(serviceName);
        return RestServiceResponse.generate(serviceName,responses);
    }

    @Autowired
    StrategyContainerService containerService;

    @Override
    public RestServiceContainer getRestServiceContainer(@PathVariable(name = "serviceName",required = true)String serviceName) {
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
    @Autowired
    StrategyRecordService recordService;
    @Override
    public AutomaticList getAutomaticList() {
        AutomaticList automaticList = new AutomaticList(recordService.getAutomaticList());
        return automaticList;
    }

    @Override
    public ManualList getManualList() {
        ManualList manualList = new ManualList(recordService.getManualList());
        return manualList;
    }

    @Autowired
    StrategyTimePeriodService timePeriodService;
    @Override
    public CommonReply updateStrategyTimePeriod(@RequestParam(name = "serviceName") String serviceName, @RequestParam(name = "peek") String peek, @RequestParam(name = "thought") String thought, @RequestParam(name = "switch") boolean sswitch) {
        timePeriodService.insertStrategy(serviceName,peek,thought,sswitch);
        return CommonReplyBuilder.buildSuccessReply();
    }

    @Autowired
    StrategyVisitorLimitService visitorLimitService;
    @Override
    public CommonReply updateStrategyVisitorLimit(@RequestParam(name = "serviceName")String serviceName,@RequestParam(name = "upper")long upper,@RequestParam(name = "lower")long lower, @RequestParam(name = "switch") boolean sswitch) {
        visitorLimitService.insertStrategy(serviceName,upper,lower,sswitch);
        return CommonReplyBuilder.buildSuccessReply();
    }

    @Autowired
    StrategyVisitorAverageService visitorAverageService;
    @Override
    public CommonReply updateStrategyVisitorAverage(@RequestParam(name = "methodName")String methodName,@RequestParam(name = "upper")long upper,@RequestParam(name = "lower")long lower, @RequestParam(name = "switch") boolean sswitch) {
        visitorAverageService.insertStrategy(methodName,upper,lower,sswitch);
        return CommonReplyBuilder.buildSuccessReply();
    }

    @Override
    public CommonReply updateStrategyOverallSwitch(String strategyName, boolean sswitch) {
        return null;
    }

    @Autowired
    StrategyTimeWindowService responseTimeService;
    @Override
    public CommonReply updateStrategyTimeWindow(@RequestParam(name = "methodName")String methodName,@RequestParam(name = "lower")int lower,@RequestParam(name = "upper")int upper,@RequestParam(name = "upperLimit")int upperLimit){
        responseTimeService.insertStrategy(methodName,lower,upper,upperLimit);
        return CommonReplyBuilder.buildSuccessReply();
    }


    @Autowired
    StrategyOverallSwitchService overallSwitchService;
    @Override
    public Overview getOverview() {
        Overview overview = new Overview();

        overview.getTimeWindow().setName(TimeWindow.name);
        overview.getTimeWindow().setStatus(overallSwitchService.getSwitchStatus(TimeWindow.name));

        List<Strategy_timePeriod> timePeriods = timePeriodService.getAllstrategys();
        Overview.StrategyService strategyService = new Overview.StrategyService();
        strategyService.setName(ServiceTimePeriod.name);
        if(timePeriods!=null) {
            for (Strategy_timePeriod timePeriod : timePeriods) {
                Overview.Status status = new Overview.Status();
                status.setName(timePeriod.getServiceName());
                status.setStatus(timePeriod.getOnOrOff());
                strategyService.getStrategys().add(status);
            }
        }
        overview.getStrategyServices().add(strategyService);

        List<Strategy_visitorLimit> visitorLimits = visitorLimitService.getAllstrategys();
        strategyService = new Overview.StrategyService();
        strategyService.setName(ServiceVisitorLimit.name);
        if(visitorLimits!=null) {
            for (Strategy_visitorLimit visitorLimit : visitorLimits) {
                Overview.Status status = new Overview.Status();
                status.setName(visitorLimit.getServiceName());
                status.setStatus(visitorLimit.getOnOrOff());
                strategyService.getStrategys().add(status);
            }
        }
        overview.getStrategyServices().add(strategyService);


        List<Strategy_visitorAverage> visitorAverages = visitorAverageService.getAllstrategys();
        if (visitorAverages!=null) {
            for (Strategy_visitorAverage visitorAverage : visitorAverages) {
                Overview.Status status = new Overview.Status();
                status.setName(visitorAverage.getMethodName());
                status.setStatus(visitorAverage.getOnOrOff());
                overview.getStrategyMethods().add(status);
            }
        }

        return overview;
    }

    @Override
    public List<Strategy_timeWindow> getTimeWindow() {
        return responseTimeService.getAllStrategy();
    }

    @Override
    public List<Strategy_timePeriod> getTimePeriod() {
        return timePeriodService.getAllstrategys();
    }

    @Override
    public List<Strategy_visitorLimit> getVisitorLimit() {
        return visitorLimitService.getAllstrategys();
    }

    @Override
    public List<Strategy_visitorAverage> getMethodAverage() {
        return visitorAverageService.getAllstrategys();
    }


    @RequestMapping("/test")
    public String test(){
        LinkedList<AdapterService> adapterServices = new LinkedList<>();

        AdapterService adapterService = new AdapterService();
        adapterService.setName("ServiceA");
        adapterServices.add(adapterService);

        adapterService = new AdapterService();
        adapterService.setName("ServiceD");

        adapterServices.add(adapterService);
        SimplifyService simplifyService = new SimplifyService();
        simplifyService.setAdapterServices(adapterServices);
        adapter.simplifyOverloadList(simplifyService);
        return "test";
    }
}
