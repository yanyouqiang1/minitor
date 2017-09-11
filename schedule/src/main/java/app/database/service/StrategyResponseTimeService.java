package app.database.service;

import app.Schedule.strategy.overall.MethodResponseTime;
import app.database.dao.StrategyResponseTimeRepository;
import app.database.domain.Strategy_responseTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
