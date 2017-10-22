package app.database.dao;

import app.database.domain.Strategy_timePeriod;
import app.database.domain.Strategy_visitorLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/9/8.
 */
@Transactional
public interface StrategyVisitorLimitRepository extends JpaRepository<Strategy_visitorLimit,Integer> {
    @Query("select v from Strategy_visitorLimit v where v.serviceName=?1 and v.onOrOff=?2")
    public Strategy_visitorLimit getByServiceNameEqualsAndOnOrOnOrOffEquals(String serviceName,boolean onOrOff);
}
