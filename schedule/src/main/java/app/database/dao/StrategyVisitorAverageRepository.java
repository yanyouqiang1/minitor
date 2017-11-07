package app.database.dao;

import app.database.domain.Strategy_visitorAverage;
import app.database.domain.Strategy_visitorLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
@Transactional
public interface StrategyVisitorAverageRepository extends JpaRepository<Strategy_visitorAverage,Integer> {
    @Query("select v from Strategy_visitorAverage v where v.methodName=?1 and v.onOrOff=?2")
    public Strategy_visitorAverage getByMethodNameEqualsAndOnOrOnOrOffEquals(String serviceName,boolean onOrOff);

    public Strategy_visitorAverage findByMethodName(String methodName);

    @Modifying
    @Query("update Strategy_visitorAverage v set v.upper=?1,v.lower=?2,v.onOrOff=?3 where v.methodName=?4")
    public Integer updateData(long upper,long lower,boolean onOrOff,String serviceName);

    public List<Strategy_visitorAverage> findByIdNotNull();
}

