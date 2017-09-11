package app.Schedule;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */
@Data
public abstract class AbstractMethod {
    private String name;

    private List<MethodSingleStrategyInter> methodSingleStrategyInterList = new LinkedList<MethodSingleStrategyInter>();

    public void addStrategy(MethodSingleStrategyInter strategyInter){
        methodSingleStrategyInterList.add(strategyInter);
    }
}
