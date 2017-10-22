package app.database.service;

import app.database.dao.StrategyOverallSwitchRepository;
import app.database.domain.Strategy_overallSwitch;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/10.
 */
@Service
public class StrategyOverallSwitchService {
    @Autowired
    StrategyOverallSwitchRepository overallSwitchRepository;

    public boolean getSwitchStatus(String strategyName){
        Strategy_overallSwitch overallSwitch = overallSwitchRepository.getByStrategyNameEquals(strategyName);
        if(overallSwitch!=null){
            return overallSwitch.getOnOrOff();
        }else
            return false;
    }
}
