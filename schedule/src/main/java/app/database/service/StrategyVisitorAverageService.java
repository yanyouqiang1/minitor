package app.database.service;

import app.Schedule.strategy.single.method.MethodVisitorAverage;
import app.database.dao.StrategyVisitorAverageRepository;
import app.database.domain.Strategy_visitorAverage;
import app.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/9.
 */
@Component
public class StrategyVisitorAverageService {
    @Autowired
    StrategyVisitorAverageRepository visitorAverageRepository;

    public MethodVisitorAverage getStrategy(String serviceName){
        Strategy_visitorAverage visitorAverage = visitorAverageRepository.getByServiceNameEqualsAndOnOrOnOrOffEquals(serviceName,true);
        if(visitorAverage!=null) {
            MethodVisitorAverage average = SpringUtil.getBean(MethodVisitorAverage.class);
            average.setUpper(visitorAverage.getUpper());
            average.setLower(visitorAverage.getLower());
//            MethodVisitorAverage average = new MethodVisitorAverage(visitorAverage.getUpper(), visitorAverage.getLower());
            return average;
        }else{
            return null;
        }
    }
}
