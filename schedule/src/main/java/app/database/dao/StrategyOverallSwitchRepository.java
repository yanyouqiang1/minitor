package app.database.dao;

import app.database.domain.Strategy_overallSwitch;
import app.database.domain.Strategy_responseTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
public interface StrategyOverallSwitchRepository extends JpaRepository<Strategy_overallSwitch,Integer> {
    public boolean getOnOrOffByStrategyNameEquals(String strategyName);
}
