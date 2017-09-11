package app.database.service;

import app.database.dao.StrategyOverallSwitchRepository;
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
        return overallSwitchRepository.getOnOrOffByStrategyNameEquals(strategyName);
    }
}
