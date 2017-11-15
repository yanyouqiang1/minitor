package app.Schedule.implementation;

import app.Schedule.AbstractHeart;
import app.Schedule.AbstractMethod;
import app.Schedule.AbstractService;
import app.Schedule.OverallStrategyInter;
import app.Schedule.strategy.overall.TimeWindow;
import app.Schedule.strategy.single.method.MethodVisitorAverage;
import app.Schedule.strategy.single.service.ServiceTimePeriod;
import app.Schedule.strategy.single.service.ServiceVisitorLimit;
import app.database.service.*;
import app.feignclient.monitor.Monitor;
import app.feignclient.monitor.MonitorMethod;
import app.feignclient.targetAdapter.AdapterService;
import app.feignclient.targetAdapter.Adapter;
import app.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/29.
 */
@Component
public class Heart extends AbstractHeart {
    @Autowired
    Adapter adapter;

    @Autowired
    Monitor monitor;

    @Override
    protected List<AbstractService> getAllStrategyService() {
        List<AdapterService> adapterServices = adapter.getServices();
        List<AbstractService> abstractServices = new LinkedList<AbstractService>();
        for (AdapterService service : adapterServices) {
            AbstractService abstractService = AbstractService.generate(service);
            addServiceStrategy(abstractService);
            abstractServices.add(abstractService);
        }
        return abstractServices;
    }

    @Autowired
    StrategyTimePeriodService timePeriodService;

    @Autowired
    StrategyVisitorLimitService visitorLimitService;

    private void addServiceStrategy(AbstractService abstractService) {
        ServiceTimePeriod serviceTimePeriod = timePeriodService.getStrategy(abstractService.getName());
        if (serviceTimePeriod != null) {
            abstractService.addStrategy(serviceTimePeriod);
        }

        ServiceVisitorLimit serviceVisitorLimit = visitorLimitService.getStrategy(abstractService.getName());
        if (serviceVisitorLimit != null) {
            abstractService.addStrategy(serviceVisitorLimit);
        }
    }


    @Override
    protected List<AbstractMethod> getStrategyMethods(AbstractService service) {
        if(service.getName()==null){
            return null;
        }
        List<MonitorMethod> monitorMethodList = monitor.getServiceMethods(service.getName());
        if(monitorMethodList==null){
            return null;
        }
        List<AbstractMethod> abstractMethods = new LinkedList<AbstractMethod>();
        for (MonitorMethod monitorMethod : monitorMethodList) {
            AbstractMethod abstractMethod = MonitorMethod.change(monitorMethod);
            addMethodStrategy(abstractMethod);
            abstractMethods.add(abstractMethod);
        }
        return abstractMethods;
    }


    @Autowired
    StrategyVisitorAverageService visitorAverageService;

    private void addMethodStrategy(AbstractMethod abstractMethod) {
        MethodVisitorAverage average = visitorAverageService.getStrategy(abstractMethod.getName());
        if (average != null) {
            abstractMethod.addStrategy(average);
        }
    }

    @Autowired
    StrategyOverallSwitchService overallSwitchService;

    @Autowired
    StrategyTimeWindowService responseTimeService;

    @Override
    protected List<OverallStrategyInter> getOverallStrategy() {
        List<OverallStrategyInter> overallStrategyInterList = new LinkedList<OverallStrategyInter>();
        if (overallSwitchService.getSwitchStatus(TimeWindow.name)){
            Map<String, TimeWindow.ServiceParameter> parameterMap = responseTimeService.getParameters();
            TimeWindow timeWindow = SpringUtil.getBean(TimeWindow.class);
            timeWindow.setParameterMap(parameterMap);
//            TimeWindow timeWindow = new TimeWindow(parameterMap);
            overallStrategyInterList.add(timeWindow);
        }
        return overallStrategyInterList;
    }

    @Override
    protected boolean decline(AbstractService service) {
        if (adapter.downService(service.getName())){
            System.out.println(service.getName()+"容器下降");
            return true;
        }else
            return false;

    }

    @Override
    protected boolean upgrade(AbstractService service) {
        if (adapter.upService(service.getName())){
            System.out.println(service.getName()+"容器提升");
            return true;
        }else
            return false;
    }
}

