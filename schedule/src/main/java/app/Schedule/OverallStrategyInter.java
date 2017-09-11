package app.Schedule;

import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface OverallStrategyInter {
    public StrategyOverallResult doStrategy(List<AbstractService> serviceList);
}
