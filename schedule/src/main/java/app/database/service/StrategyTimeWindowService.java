package app.database.service;

import app.Schedule.strategy.overall.NativeStrategy;
import app.database.dao.StrategyOverallSwitchRepository;
import app.database.dao.StrategyTimeWindowRepository;
import app.database.domain.Strategy_overallSwitch;
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

    public Map<String, NativeStrategy.ServiceParameter> getParameters() {
        List<Strategy_timeWindow> responseTimes = timeWindowRepository.getAllByIdNotNull();
        if (responseTimes == null) return null;
        Map<String, NativeStrategy.ServiceParameter> map = new HashMap<String, NativeStrategy.ServiceParameter>();
        for (Strategy_timeWindow responseTime : responseTimes) {
            NativeStrategy.ServiceParameter parameter = new NativeStrategy.ServiceParameter(responseTime.getUpper(), responseTime.getLower(), responseTime.getUpperLimit());
            map.put(responseTime.getServiceName(), parameter);
        }
        return map;
    }

    public void insertStrategy(String methodName, int upper, int lower, int upperLimit) {
        update(methodName, upper, lower, upperLimit);
    }

    private void update(String serviceName, int upper, int lower, int upperLimit) {
        Object service = timeWindowRepository.findByServiceName(serviceName);
        if (service == null) {
            Strategy_timeWindow responseTime = new Strategy_timeWindow();
            responseTime.setLower(lower);
            responseTime.setUpdateTime(new Date());
            responseTime.setUpper(upper);
            responseTime.setUpperLimit(upperLimit);
            responseTime.setServiceName(serviceName);
            timeWindowRepository.save(responseTime);
        } else {
            timeWindowRepository.updateData(upper, lower, upperLimit, serviceName);
        }
    }

    public List<Strategy_timeWindow> getAllStrategy() {
        return timeWindowRepository.getAllByIdNotNull();
    }


    @Autowired
    StrategyOverallSwitchRepository overallSwitchRepository;

    public boolean changeStatus(String strategyName) {
        Strategy_overallSwitch overallSwitch = overallSwitchRepository.getByStrategyNameEquals(strategyName);
        if (overallSwitch != null)
            overallSwitchRepository.changeStatus(!overallSwitch.getOnOrOff(), strategyName);
        return true;
    }
}
