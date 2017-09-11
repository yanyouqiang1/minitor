package app.database.service;

import app.Schedule.strategy.single.service.ServiceVisitorLimit;
import app.database.dao.StrategyVisitorLimitRepository;
import app.database.domain.Strategy_visitorLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/9.
 */
@Component
public class StrategyVisitorLimitService {

    @Autowired
    StrategyVisitorLimitRepository visitorLimitRepository;

    public ServiceVisitorLimit getStrategy(String serviceName){
        Strategy_visitorLimit visitorLimit = visitorLimitRepository.getByServiceNameEqualsAndOnOrOnOrOffEquals(serviceName,true);
        if(visitorLimit!=null) {
            ServiceVisitorLimit limit = new ServiceVisitorLimit(visitorLimit.getUpper(), visitorLimit.getLower());
            return limit;
        }else{
            return null;
        }
    }
}
