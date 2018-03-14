package app.outerInterface;

import app.Schedule.strategy.overall.NativeStrategy;
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
import app.outerInterface.entity.re.ParamTimePeriod;
import app.outerInterface.entity.re.ParamTimeWindow;
import app.outerInterface.entity.re.ParamVisitorLimit;
import app.outerInterface.entity.reply.CommonReply;
import app.outerInterface.entity.reply.CommonReplyBuilder;
import app.outerInterface.entity.strategy.Overview;
import app.outerInterface.entity.task.AutomaticList;
import app.outerInterface.entity.task.ManualList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<AdapterService> adapterServices = adapter.getServices();
        return RestServices.generate(adapterServices);
    }

    @Override
    public RestServiceResponse getRestServiceResponse(@PathVariable(name = "serviceName", required = true) String serviceName) {
        int[] responses = monitor.getServiceRecentResponseTime(serviceName);
        return RestServiceResponse.generate(serviceName, responses);
    }

    @Autowired
    StrategyContainerService containerService;

    @Override
    public RestServiceContainer getRestServiceContainer(@PathVariable(name = "serviceName", required = true) String serviceName) {
        return RestServiceContainer.generate(serviceName, containerService.getLatestContainers(serviceName));
    }

    @Override
    public RestServiceContainerCpu getRestServiceContainerCpu(String serviceName) {
        RestServiceContainerCpu serviceContainerCpu = new RestServiceContainerCpu(serviceName);
        List<AdapterService> services = adapter.getServices();
        AdapterService findService = null;
        for (AdapterService adapterService : services) {
            if (adapterService.getName().equals(serviceName)) {
                findService = adapterService;
            }
        }
        if (findService != null) {
            List<String> containerNames = findService.getContainerNames();
            for (String name : containerNames) {
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
        AdapterService findService = null;
        for (AdapterService adapterService : services) {
            if (adapterService.getName().equals(serviceName)) {
                findService = adapterService;
            }
        }
        if (findService != null) {
            List<String> containerNames = findService.getContainerNames();
            for (String name : containerNames) {
                RestServiceContainerMemory.ContainerMemory containerMemory = new RestServiceContainerMemory.ContainerMemory(name);
                containerMemory.setData(adapter.getCpuRateByContainerName(name));
                serviceContainerMemory.getContainerMemorys().add(containerMemory);
            }

        }
        return serviceContainerMemory;
    }

    @Override
    public AutomaticList getAutomaticList(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size){
    AutomaticList automaticList = new AutomaticList(recordService.getAutomaticList(page, size));
        return automaticList;
    }

    @Override
    public ManualList getManualList(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size){
    ManualList manualList = new ManualList(recordService.getManualList(page,size));
        return manualList;
    }

    @Autowired
    StrategyTimePeriodService timePeriodService;

    @Override
    public CommonReply updateStrategyTimePeriod(@RequestBody ParamTimePeriod paramTimePeriod) {
        timePeriodService.insertStrategy(paramTimePeriod.getServiceName(), paramTimePeriod.getPeek(), paramTimePeriod.getThought(), true);
        return CommonReplyBuilder.buildSuccessReply();
    }

    @Autowired
    StrategyVisitorLimitService visitorLimitService;

    @Override
    public CommonReply updateStrategyVisitorLimit(@RequestBody ParamVisitorLimit paramVisitorLimit) {
        visitorLimitService.insertStrategy(paramVisitorLimit.getServiceName(), paramVisitorLimit.getUpper(), paramVisitorLimit.getLower(), true);
        return CommonReplyBuilder.buildSuccessReply();
    }

    @Autowired
    StrategyVisitorAverageService visitorAverageService;

    @Override
    public CommonReply updateStrategyVisitorAverage(@RequestParam(name = "methodName") String methodName, @RequestParam(name = "upper") long upper, @RequestParam(name = "lower") long lower, @RequestParam(name = "switch") boolean sswitch) {
        visitorAverageService.insertStrategy(methodName, upper, lower, sswitch);
        return CommonReplyBuilder.buildSuccessReply();
    }

    @Autowired
    StrategyTimeWindowService timeWindowService;

    @Override
    public CommonReply updateStrategyTimeWindow(@RequestBody ParamTimeWindow paramTimeWindow) {
        timeWindowService.insertStrategy(paramTimeWindow.getServiceName(), paramTimeWindow.getUpper(), paramTimeWindow.getLower(), paramTimeWindow.getUpperLimit());
        return CommonReplyBuilder.buildSuccessReply();
    }


    @Autowired
    StrategyOverallSwitchService overallSwitchService;

    @Override
    public Overview getOverview() {
        Overview overview = new Overview();

        overview.getTimeWindow().setName(NativeStrategy.name);
        overview.getTimeWindow().setStatus(overallSwitchService.getSwitchStatus(NativeStrategy.name));

        List<Strategy_timePeriod> timePeriods = timePeriodService.getAllstrategys();
        Overview.StrategyService strategyService = new Overview.StrategyService();
        strategyService.setName(ServiceTimePeriod.name);
        if (timePeriods != null) {
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
        if (visitorLimits != null) {
            for (Strategy_visitorLimit visitorLimit : visitorLimits) {
                Overview.Status status = new Overview.Status();
                status.setName(visitorLimit.getServiceName());
                status.setStatus(visitorLimit.getOnOrOff());
                strategyService.getStrategys().add(status);
            }
        }
        overview.getStrategyServices().add(strategyService);


        List<Strategy_visitorAverage> visitorAverages = visitorAverageService.getAllstrategys();
        if (visitorAverages != null) {
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
        return timeWindowService.getAllStrategy();
    }

    @Override
    public Strategy_timePeriod getTimePeriod(@RequestParam("serviceName") String serviceName) {
        return timePeriodService.getStrategyInfo(serviceName);
    }

    @Override
    public Strategy_visitorLimit getVisitorLimit(@RequestParam("serviceName") String serviceName) {
        return visitorLimitService.getStrategyInfo(serviceName);
    }

    @Override
    public List<Strategy_visitorAverage> getMethodAverage() {
        return visitorAverageService.getAllstrategys();
    }

    @Override
    public CommonReply changeStrategyTimeWindow() {
        if (timeWindowService.changeStatus(NativeStrategy.name))
            return CommonReplyBuilder.buildSuccessReply();
        return CommonReplyBuilder.buildErrorReply("unkown error");

    }

    @Override
    public CommonReply changeStrategyServiceTimeperiod(@PathVariable("serviceName") String serviceName) {
        if (timePeriodService.changeStatus(serviceName))
            return CommonReplyBuilder.buildSuccessReply();
        return CommonReplyBuilder.buildErrorReply("unkown error");
    }

    @Override
    public CommonReply changeStrategyServiceVisitorLimit(@PathVariable("serviceName") String serviceName) {
        if (visitorLimitService.changeStatus(serviceName))
            return CommonReplyBuilder.buildSuccessReply();
        return CommonReplyBuilder.buildErrorReply("unkown error");
    }

    @Autowired
    StrategyRecordService recordService;

    @Override
    public CommonReply manualServiceUp(String serviceName) {
        if (adapter.upService(serviceName)) {
            recordService.recordManualUp(serviceName);
            return CommonReplyBuilder.buildSuccessReply();
        } else
            return CommonReplyBuilder.buildErrorReply("adapter error");
    }

    @Override
    public CommonReply manualServiceDown(String serviceName) {
        if (adapter.downService(serviceName)) {
            recordService.recordManualDown(serviceName);
            return CommonReplyBuilder.buildSuccessReply();
        } else
            return CommonReplyBuilder.buildErrorReply("adapter error");
    }

    @RequestMapping("/test")
    public String test() {
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
