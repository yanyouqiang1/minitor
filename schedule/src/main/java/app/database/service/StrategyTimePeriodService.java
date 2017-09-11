package app.database.service;

import app.Schedule.strategy.single.service.ServiceTimePeriod;
import app.database.dao.StrategyTimePeriodRepository;
import app.database.domain.Strategy_timePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/8.
 */
@Component
public class StrategyTimePeriodService {
    @Autowired
    StrategyTimePeriodRepository timePeriodRepository;

    public ServiceTimePeriod getStrategy(String serviceName){
        Strategy_timePeriod strategyTimePeriod = timePeriodRepository.getByServiceNameEqualsAndOnOrOnOrOffEquals(serviceName,true);
        if(strategyTimePeriod!=null) {
            ServiceTimePeriod serviceTimePeriod = new ServiceTimePeriod(strategyTimePeriod.getPeekTime(), strategyTimePeriod.getThoughTime());
            serviceTimePeriod.setPeakHandle(strategyTimePeriod.getPeekHandle());
            serviceTimePeriod.setThoughHandle(strategyTimePeriod.getThoughHandle());
            return serviceTimePeriod;
        }else{
            return null;
        }
    }
}
