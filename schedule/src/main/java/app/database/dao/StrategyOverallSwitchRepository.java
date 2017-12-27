package app.database.dao;

import app.database.domain.Strategy_overallSwitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/9/10.
 */
@Transactional
public interface StrategyOverallSwitchRepository extends JpaRepository<Strategy_overallSwitch,Integer> {
    public Strategy_overallSwitch getByStrategyNameEquals(String strategyName);

    @Modifying
    @Query("update Strategy_overallSwitch over set over.onOrOff = ?1 where over.strategyName=?2")
    public void changeStatus(Boolean onOrOff,String strategyName);
}
