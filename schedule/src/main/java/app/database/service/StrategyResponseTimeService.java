package app.database.service;

import app.Schedule.strategy.overall.MethodResponseTime;
import app.database.dao.StrategyResponseTimeRepository;
import app.database.domain.Strategy_responseTime;
import app.database.domain.Strategy_visitorAverage;
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
public class StrategyResponseTimeService {
    @Autowired
    StrategyResponseTimeRepository responseTimeRepository;

    public Map<String,MethodResponseTime.MethodParameter> getParameters(){
        List<Strategy_responseTime> responseTimes = responseTimeRepository.getAllByIdNotNull();
        Map<String,MethodResponseTime.MethodParameter> map = new HashMap<String, MethodResponseTime.MethodParameter>();
        for(Strategy_responseTime responseTime:responseTimes){
            MethodResponseTime.MethodParameter parameter = new MethodResponseTime.MethodParameter(responseTime.getUpper(),responseTime.getLower(),responseTime.getUpperLimit());
            map.put(responseTime.getMethodName(),parameter);
        }
        return map;
    }
    public void insertStrategy(String methodName,int upper,int lower,int upperLimit){
        update(methodName,upper,lower,upperLimit);
    }

    private void update(String methodName,int upper,int lower,int upperLimit){
        Object service = responseTimeRepository.findByMethodName(methodName);
        if(service==null){
            Strategy_responseTime responseTime = new Strategy_responseTime();
            responseTime.setLower(lower);
            responseTime.setUpdateTime(new Date());
            responseTime.setUpper(upper);
            responseTime.setUpperLimit(upperLimit);
            responseTime.setMethodName(methodName);
            responseTimeRepository.save(responseTime);
        }else{
            responseTimeRepository.updateData(upper,lower,upperLimit,methodName);
        }
    }

    public List<Strategy_responseTime> getAllStrategy(){
        return responseTimeRepository.getAllByIdNotNull();
    }
}
