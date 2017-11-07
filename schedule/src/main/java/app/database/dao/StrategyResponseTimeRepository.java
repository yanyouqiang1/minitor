package app.database.dao;

import app.database.domain.Strategy_responseTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
@Transactional
public interface StrategyResponseTimeRepository extends JpaRepository<Strategy_responseTime,Integer> {
    public List<Strategy_responseTime> getAllByIdNotNull();

    public Strategy_responseTime findByMethodName(String methodName);

    @Modifying
    @Query("update Strategy_responseTime r set r.upper=?1,r.lower=?2,r.upperLimit=?3 where r.methodName=?4")
    public Integer updateData(int upper,int lower,int upperLimit,String methodName);


}
