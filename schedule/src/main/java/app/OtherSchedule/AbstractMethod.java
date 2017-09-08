package app.OtherSchedule;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */
@Data
public abstract class AbstractMethod {
    private List<MethodOverallStrategyInter> methodOverallStrategyInterList = new LinkedList<MethodOverallStrategyInter>();
    private List<MethodSingleStrategyInter> methodSingleStrategyInterList = new LinkedList<MethodSingleStrategyInter>();
}
