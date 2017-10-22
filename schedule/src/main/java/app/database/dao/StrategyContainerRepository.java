package app.database.dao;

import app.database.domain.Strategy_container;
import app.database.domain.Strategy_overallSwitch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
public interface StrategyContainerRepository extends JpaRepository<Strategy_container,Integer> {
    public List<Strategy_container> findTopByServiceNameOrderByIdDesc(String serviceName);
}
