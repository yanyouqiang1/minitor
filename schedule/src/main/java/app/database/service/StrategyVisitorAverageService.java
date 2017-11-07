package app.database.service;

import app.Schedule.strategy.single.method.MethodVisitorAverage;
import app.database.dao.StrategyVisitorAverageRepository;
import app.database.domain.Strategy_visitorAverage;
import app.database.domain.Strategy_visitorLimit;
import app.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
@Component
public class StrategyVisitorAverageService {
    @Autowired
    StrategyVisitorAverageRepository visitorAverageRepository;

    public MethodVisitorAverage getStrategy(String serviceName){
        Strategy_visitorAverage visitorAverage = visitorAverageRepository.getByMethodNameEqualsAndOnOrOnOrOffEquals(serviceName,true);
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

    public void insertStrategy(String methodName,long upper,long lower,boolean sswitch){
        update(methodName,upper,lower,sswitch);
    }

    private void update(String methodName,long upper,long lower,boolean onOroff){
        Object service = visitorAverageRepository.findByMethodName(methodName);
        if(service==null){
            Strategy_visitorAverage visitorAverage = new Strategy_visitorAverage();
            visitorAverage.setMethodName(methodName);
            visitorAverage.setOnOrOff(onOroff);
            visitorAverage.setLower(lower);
            visitorAverage.setUpper(upper);
            visitorAverageRepository.save(visitorAverage);
        }else{
            visitorAverageRepository.updateData(upper,lower,onOroff,methodName);
        }
    }

    public List<Strategy_visitorAverage> getAllstrategys(){
        return visitorAverageRepository.findByIdNotNull();
    }
}
