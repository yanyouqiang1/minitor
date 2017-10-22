package app.Schedule.strategy.single.method;

import app.Schedule.AbstractMethod;
import app.Schedule.AbstractService;
import app.Schedule.MethodSingleStrategyInter;
import app.Schedule.StrategySingleResult;
import app.feignclient.monitor.Monitor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/9/7.
 */
public class MethodVisitorAverage implements MethodSingleStrategyInter{
    private long upper,lower;

    public MethodVisitorAverage(long upper, long lower) {
        this.upper = upper;
        this.lower = lower;
    }


    @Autowired
    Monitor monitor;

    @Override
    public StrategySingleResult doStrategy(AbstractMethod method, AbstractService abstractService) {
        long visitors = monitor.getMethodLatestVisitor(abstractService.getName(),method.getName());
        long avg = visitors/abstractService.getScale();
        if(avg>=upper){
            return StrategySingleResult.UP;
        }
        if(avg<=lower){
            return StrategySingleResult.DOWN;
        }
        return StrategySingleResult.NORMAL;
    }
}
