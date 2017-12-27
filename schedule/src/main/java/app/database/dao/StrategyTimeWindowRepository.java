package app.database.dao;

import app.database.domain.Strategy_timeWindow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
@Transactional
public interface StrategyTimeWindowRepository extends JpaRepository<Strategy_timeWindow,Integer> {
    public List<Strategy_timeWindow> getAllByIdNotNull();

    public Strategy_timeWindow findByServiceName(String methodName);

    @Modifying
    @Query("update Strategy_timeWindow r set r.upper=?1,r.lower=?2,r.upperLimit=?3 where r.serviceName=?4")
    public Integer updateData(int upper,int lower,int upperLimit,String serviceName);

}
