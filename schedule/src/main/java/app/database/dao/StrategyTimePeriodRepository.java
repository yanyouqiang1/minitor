package app.database.dao;

import app.database.domain.Strategy_timePeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/9/8.
 */
@Transactional
public interface StrategyTimePeriodRepository extends JpaRepository<Strategy_timePeriod,Integer> {
    public Strategy_timePeriod getByServiceNameEqualsAndOnOrOnOrOffEquals(String serviceName,boolean onOrOff);
}
