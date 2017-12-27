package app.database.service;

import app.Schedule.strategy.single.service.ServiceVisitorLimit;
import app.database.dao.StrategyVisitorLimitRepository;
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
public class StrategyVisitorLimitService {

    @Autowired
    StrategyVisitorLimitRepository visitorLimitRepository;

    public ServiceVisitorLimit getStrategy(String serviceName) {
        Strategy_visitorLimit visitorLimit = visitorLimitRepository.getByServiceNameEqualsAndOnOrOnOrOffEquals(serviceName, true);
        if (visitorLimit != null) {
            ServiceVisitorLimit limit = SpringUtil.getBean(ServiceVisitorLimit.class);
            limit.setUpper(visitorLimit.getUpper());
            limit.setLower(visitorLimit.getLower());
            return limit;
        } else {
            return null;
        }
    }

    public void insertStrategy(String serviceName, long upper, long lower, boolean sswitch) {
        update(serviceName, upper, lower, sswitch);
    }

    private void update(String serviceName, long upper, long lower, boolean onOroff) {
        Object service = visitorLimitRepository.findByServiceName(serviceName);
        if (service == null) {
            Strategy_visitorLimit visitorLimit = new Strategy_visitorLimit();
            visitorLimit.setUpdateTime(new Date());
            visitorLimit.setUpper(upper);
            visitorLimit.setLower(lower);
            visitorLimit.setOnOrOff(true);
            visitorLimit.setServiceName(serviceName);
            visitorLimitRepository.save(visitorLimit);
        } else {
            visitorLimitRepository.updateData(upper, lower, onOroff, serviceName);
        }
    }

    public List<Strategy_visitorLimit> getAllstrategys() {
        return visitorLimitRepository.findByIdNotNull();
    }

    public boolean changeStatus(String serviceName) {
        Strategy_visitorLimit visitorLimit = visitorLimitRepository.findByServiceName(serviceName);
        if (visitorLimit != null)
            visitorLimitRepository.changeStatus(!visitorLimit.getOnOrOff(), serviceName);
        return true;
    }

    public Strategy_visitorLimit getStrategyInfo(String serviceName) {
        Strategy_visitorLimit visitorLimit = visitorLimitRepository.findByServiceName(serviceName);
        if(visitorLimit==null){
            visitorLimit = new Strategy_visitorLimit(serviceName);
        }
        return visitorLimit;
    }
}
