package app.Schedule.strategy.single.service;

import app.Schedule.AbstractService;
import app.Schedule.ServiceSingleStrategyInter;
import app.Schedule.StrategySingleResult;
import app.feignclient.monitor.Monitor;
import app.feignclient.targetAdapter.Adapter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/30.
 * 平均访问量控制
 */
@Scope("prototype")
@Component
@Data
public class ServiceVisitorLimit implements ServiceSingleStrategyInter {
    public static String name="service visitor limit";
    private long upper,lower;

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

    @Override
    public String getStrategyName() {
        return name;
    }
}
