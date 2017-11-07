package app.database.service;

import app.Schedule.strategy.single.service.ServiceTimePeriod;
import app.database.dao.StrategyTimePeriodRepository;
import app.database.domain.Strategy_timePeriod;
import app.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
            ServiceTimePeriod serviceTimePeriod = SpringUtil.getBean(ServiceTimePeriod.class);
            serviceTimePeriod.setPeek(strategyTimePeriod.getPeekTime());
            serviceTimePeriod.setThough(strategyTimePeriod.getThoughTime());
            serviceTimePeriod.setPeakHandle(strategyTimePeriod.getPeekHandle());
            serviceTimePeriod.setThoughHandle(strategyTimePeriod.getThoughHandle());
            serviceTimePeriod.setPeakHandle(strategyTimePeriod.getPeekHandle());
            serviceTimePeriod.setThoughHandle(strategyTimePeriod.getThoughHandle());
            return serviceTimePeriod;
        }else{
            return null;
        }
    }

    public void insertStrategy(String serviceName,String peekTime,String thoughTime,boolean sswitch){
        Object service = timePeriodRepository.findIdByServiceName(serviceName);
        if(service==null) {
            Strategy_timePeriod timePeriod = new Strategy_timePeriod();
            timePeriod.setServiceName(serviceName);
            timePeriod.setPeekTime(peekTime);
            timePeriod.setPeekHandle(false);
            timePeriod.setThoughTime(thoughTime);
            timePeriod.setThoughHandle(false);
            timePeriod.setOnOrOff(sswitch);
            timePeriodRepository.save(timePeriod);
        }else{
            update(serviceName,peekTime,thoughTime,false,false,sswitch);
        }
    }

    public void updateStatus(String serviceName,boolean isPeekHandle,boolean isThoughHandle){
        timePeriodRepository.updateStatus(isPeekHandle,isThoughHandle,serviceName);
    }


    private void update(String serviceName,String peekTime,String thoughTime,boolean isPeekHandle,boolean isThoughHandle,boolean sswitch){
        timePeriodRepository.updateAll(peekTime,thoughTime,isPeekHandle,isThoughHandle,sswitch,serviceName);
    }

    public List<Strategy_timePeriod> getAllstrategys(){
         return timePeriodRepository.findByIdNotNull();
    }
}
