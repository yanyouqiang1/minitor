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
        Strategy_timePeriod strategyTimePeriod = timePeriodRepository.findByServiceNameAndOnOrOffEquals(serviceName,true);
        if(strategyTimePeriod!=null) {
            ServiceTimePeriod serviceTimePeriod = new ServiceTimePeriod(this,strategyTimePeriod.getPeekTime(), strategyTimePeriod.getThoughTime());
            serviceTimePeriod.setPeakHandle(strategyTimePeriod.getPeekHandle());
            serviceTimePeriod.setThoughHandle(strategyTimePeriod.getThoughHandle());
            serviceTimePeriod.setPeakHandle(strategyTimePeriod.getPeekHandle());
            serviceTimePeriod.setThoughHandle(strategyTimePeriod.getThoughHandle());
            return serviceTimePeriod;
        }else{
            return null;
        }
//        return null;
    }

    public void insertStrategy(String serviceName,String peekTime,String thoughTime){
        Object service = timePeriodRepository.findIdByServiceName(serviceName);
        if(service==null) {
            Strategy_timePeriod timePeriod = new Strategy_timePeriod();
            timePeriod.setServiceName(serviceName);
            timePeriod.setPeekTime(peekTime);
            timePeriod.setPeekHandle(false);
            timePeriod.setThoughTime(thoughTime);
            timePeriod.setThoughHandle(false);
            timePeriod.setOnOrOff(true);
            timePeriodRepository.save(timePeriod);
        }else{
            update(serviceName,peekTime,thoughTime,false,false);
        }
    }

    public void updateStatus(String serviceName,boolean isPeekHandle,boolean isThoughHandle){
        timePeriodRepository.updateStatus(isPeekHandle,isThoughHandle,serviceName);
    }


    private void update(String serviceName,String peekTime,String thoughTime,boolean isPeekHandle,boolean isThoughHandle){
        timePeriodRepository.updateAll(peekTime,thoughTime,isPeekHandle,isThoughHandle,serviceName);
    }
}
