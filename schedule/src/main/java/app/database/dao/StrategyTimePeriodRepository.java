package app.database.dao;

import app.database.domain.Strategy_timePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */
@Transactional
public interface StrategyTimePeriodRepository extends JpaRepository<Strategy_timePeriod,Integer> {
//    @Query("SELECT from Strategy_timePeriod where serviceName=? and onOrOff=?")
//    public Strategy_timePeriod getFirstByServiceNameEqualsAndOnOrOffEquals(String serviceName, boolean onOrOff);
    @Query("select t from Strategy_timePeriod t where t.serviceName=?1 and t.onOrOff=?2")
    public Strategy_timePeriod findByServiceNameAndOnOrOffEquals(String serviceName,Boolean onOrOff);

    @Modifying
    @Query("update Strategy_timePeriod t set t.peekTime=?1,t.thoughTime=?2,t.peekHandle=?3,t.thoughHandle=?4,t.onOrOff=?5 where t.serviceName=?6")
    public Integer updateAll(String peekTime,String though,boolean peekHandle,boolean thoughtHandle,boolean sswitch,String serviceName);

    @Modifying
    @Query("update Strategy_timePeriod t set t.peekHandle=?1,t.thoughHandle=?2 where t.serviceName=?3")
    public Integer updateStatus(boolean peekHandle,boolean thoughtHandle,String serviceName);


    public Strategy_timePeriod findIdByServiceName(String serviceName);

    public List<Strategy_timePeriod> findByIdNotNull();

    @Modifying
    @Query("update Strategy_timePeriod t set t.onOrOff=?1 where t.serviceName=?2")
    public void changeSwitch(Boolean onOrOff,String serviceName);

    public Strategy_timePeriod findByServiceName(String serviceName);
}
