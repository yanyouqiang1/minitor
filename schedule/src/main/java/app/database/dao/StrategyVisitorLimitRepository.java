package app.database.dao;

import app.database.domain.Strategy_timePeriod;
import app.database.domain.Strategy_visitorLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/9/8.
 */
@Transactional
public interface StrategyVisitorLimitRepository extends JpaRepository<Strategy_visitorLimit,Integer> {
    public Strategy_visitorLimit getByServiceNameEqualsAndOnOrOnOrOffEquals(String serviceName,boolean onOrOff);
}
