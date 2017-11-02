package app.Schedule.strategy.single.service;

import app.Schedule.AbstractService;
import app.Schedule.ServiceSingleStrategyInter;
import app.Schedule.StrategySingleResult;
import app.feignclient.monitor.Monitor;
import app.feignclient.targetAdapter.Adapter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/8/30.
 * 平均访问量控制
 */
public class ServiceVisitorLimit implements ServiceSingleStrategyInter {
    private long upper,lower;

    public ServiceVisitorLimit(long upper, long lower) {
        this.upper = upper;
        this.lower = lower;
    }

    @Autowired
    Monitor monitor;

    @Override
    public StrategySingleResult doStrategy(AbstractService service) {
        long requestVisitor = monitor.getServiceLatestVisitor(service.getName());
        long avg = requestVisitor/service.getScale();
        if(avg>=upper){
            return StrategySingleResult.UP;
        }
        if(avg<=lower){
            return StrategySingleResult.DOWN;
        }
        return StrategySingleResult.NORMAL;
    }

    @Override
    public void afterStrategy(AbstractService service) {

    }
}
