package app.database.dao;

import app.database.domain.Strategy_visitorAverage;
import app.database.domain.Strategy_visitorLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/9/9.
 */
@Transactional
public interface StrategyVisitorAverageRepository extends JpaRepository<Strategy_visitorAverage,Integer> {
    public Strategy_visitorAverage getByServiceNameEqualsAndOnOrOnOrOffEquals(String serviceName,boolean onOrOff);
}

