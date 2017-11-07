package app.Schedule.strategy.single.service;

import app.Schedule.AbstractService;
import app.Schedule.ServiceSingleStrategyInter;
import app.Schedule.StrategySingleResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/7.
 */
@Scope("prototype")
@Component
public class ServiceResource implements ServiceSingleStrategyInter {
    public static String name="service resource";
    @Override
    public StrategySingleResult doStrategy(AbstractService service) {
        return null;
    }

    @Override
    public void afterStrategy(AbstractService service) {

    }

    @Override
    public String getStrategyName() {
        return name;
    }
}
