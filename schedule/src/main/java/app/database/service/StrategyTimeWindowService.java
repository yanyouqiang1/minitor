package app.database.service;

import app.Schedule.strategy.overall.TimeWindow;
import app.database.dao.StrategyTimeWindowRepository;
import app.database.domain.Strategy_timeWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/10.
 */
@Service
public class StrategyTimeWindowService {
    @Autowired
    StrategyTimeWindowRepository timeWindowRepository;

    public Map<String,TimeWindow.ServiceParameter> getParameters(){
        List<Strategy_timeWindow> responseTimes = timeWindowRepository.getAllByIdNotNull();
        if(responseTimes==null) return null;
        Map<String,TimeWindow.ServiceParameter> map = new HashMap<String, TimeWindow.ServiceParameter>();
        for(Strategy_timeWindow responseTime:responseTimes){
            TimeWindow.ServiceParameter parameter = new TimeWindow.ServiceParameter(responseTime.getUpper(),responseTime.getLower(),responseTime.getUpperLimit());
            map.put(responseTime.getServiceName(),parameter);
        }
        return map;
    }
    public void insertStrategy(String methodName,int upper,int lower,int upperLimit){
        update(methodName,upper,lower,upperLimit);
    }

    private void update(String serviceName,int upper,int lower,int upperLimit){
        Object service = timeWindowRepository.findByServiceName(serviceName);
        if(service==null){
            Strategy_timeWindow responseTime = new Strategy_timeWindow();
            responseTime.setLower(lower);
            responseTime.setUpdateTime(new Date());
            responseTime.setUpper(upper);
            responseTime.setUpperLimit(upperLimit);
            responseTime.setServiceName(serviceName);
            timeWindowRepository.save(responseTime);
        }else{
            timeWindowRepository.updateData(upper,lower,upperLimit,serviceName);
        }
    }

    public List<Strategy_timeWindow> getAllStrategy(){
        return timeWindowRepository.getAllByIdNotNull();
    }
}
