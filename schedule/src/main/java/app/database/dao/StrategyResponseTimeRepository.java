package app.database.dao;

import app.database.domain.Strategy_responseTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/10.
 */
public interface StrategyResponseTimeRepository extends JpaRepository<Strategy_responseTime,Integer> {
    public List<Strategy_responseTime> getAllByIdNotNull();
}
