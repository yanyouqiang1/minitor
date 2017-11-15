package app.database.dao;

import app.database.domain.Strategy_overallSwitch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/9/10.
 */
public interface StrategyOverallSwitchRepository extends JpaRepository<Strategy_overallSwitch,Integer> {
    public Strategy_overallSwitch getByStrategyNameEquals(String strategyName);
}
