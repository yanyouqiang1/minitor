package app.Schedule.strategy.single.method;

import app.Schedule.AbstractMethod;
import app.Schedule.AbstractService;
import app.Schedule.MethodSingleStrategyInter;
import app.Schedule.StrategySingleResult;
import app.feignclient.monitor.Monitor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/9/7.
 */
@Data
@Scope("prototype")
@Component
public class MethodVisitorAverage implements MethodSingleStrategyInter{
    private String name ="method visitor average";
    private long upper,lower;

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

    @Override
    public String getStrategyName() {
        return name;
    }
}
