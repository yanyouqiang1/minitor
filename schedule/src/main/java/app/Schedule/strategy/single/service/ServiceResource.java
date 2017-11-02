package app.Schedule.strategy.single.service;

import app.Schedule.AbstractService;
import app.Schedule.ServiceSingleStrategyInter;
import app.Schedule.StrategySingleResult;

/**
 * Created by Administrator on 2017/9/7.
 */
public class ServiceResource implements ServiceSingleStrategyInter {
    @Override
    public StrategySingleResult doStrategy(AbstractService service) {
        return null;
    }

    @Override
    public void afterStrategy(AbstractService service) {

    }
}
