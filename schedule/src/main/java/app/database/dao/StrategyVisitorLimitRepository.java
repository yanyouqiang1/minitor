package app.database.dao;

import app.database.domain.Strategy_timePeriod;
import app.database.domain.Strategy_visitorLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */
@Transactional
public interface StrategyVisitorLimitRepository extends JpaRepository<Strategy_visitorLimit,Integer> {
    @Query("select v from Strategy_visitorLimit v where v.serviceName=?1 and v.onOrOff=?2")
    public Strategy_visitorLimit getByServiceNameEqualsAndOnOrOnOrOffEquals(String serviceName,boolean onOrOff);

    public Strategy_visitorLimit findByServiceName(String serviceName);

    @Modifying
    @Query("update Strategy_visitorLimit v set v.upper=?1,v.lower=?2,v.onOrOff=?3 where v.serviceName=?4")
    public Integer updateData(long upper,long lower,boolean onOrOff,String serviceName);

    public List<Strategy_visitorLimit> findByIdNotNull();

    @Query("update Strategy_visitorLimit v set v.onOrOff = ?1 where v.serviceName=?2")
    public void changeStatus(Boolean onOrOff,String serviceName);

}
