package app.database.dao;

import app.database.domain.Strategy_timePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/9/8.
 */
@Transactional
public interface StrategyTimePeriodRepository extends JpaRepository<Strategy_timePeriod,Integer> {
//    @Query("SELECT from Strategy_timePeriod where serviceName=? and onOrOff=?")
//    public Strategy_timePeriod getFirstByServiceNameEqualsAndOnOrOffEquals(String serviceName, boolean onOrOff);
    @Query("select t from Strategy_timePeriod t where t.serviceName=?1 and t.onOrOff=?2")
    public Strategy_timePeriod findByServiceNameAndOnOrOffEquals(String serviceName,Boolean onOrOff);

}
