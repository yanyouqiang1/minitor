package app.OtherSchedule.strategy.service.single;

import app.OtherSchedule.AbstractService;
import app.OtherSchedule.ServiceSingleStrategyInter;
import app.OtherSchedule.StrategyOverallResult;
import app.OtherSchedule.StrategySingleResult;
import app.database.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/8/30.
 * 平均访问量控制
 */
public class VisitorLimit implements ServiceSingleStrategyInter {
    private long upper,lower;

    public VisitorLimit(long upper, long lower) {
        this.upper = upper;
        this.lower = lower;
    }

    @Autowired
    ServiceService serviceService;

    @Override
    public StrategySingleResult doStrategy(AbstractService service) {
        long requestVisitor = serviceService.getLatestRequestVisitor(service.getName());
        long avg = requestVisitor/service.getScale();
        if(avg>=upper){
            return StrategySingleResult.UP;
        }
        if(avg<=lower){
            return StrategySingleResult.DOWN;
        }
        return StrategySingleResult.NORMAL;
    }
}
