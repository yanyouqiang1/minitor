package app.Schedule.strategy.overall;

import app.Schedule.AbstractService;
import app.Schedule.OverallStrategyInter;
import app.Schedule.StrategyOverallResult;
import app.feignclient.monitor.Monitor;
import app.feignclient.targetAdapter.Adapter;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@Scope("prototype")
public class AdvanceStrategy implements OverallStrategyInter{
    public static String name ="Native strategy";

    private Map<String,ServiceParameter> parameterMap;

    public AdvanceStrategy() {
    }

    @Autowired
    Monitor monitor;

    @Autowired
    Adapter adapter;


    @Override
    public StrategyOverallResult doStrategy(List<AbstractService> serviceList) {
        return null;
    }


    @Override
    public String getStrategyName() {
        return name;
    }


    @Data
    public static class ServiceParameter {
        private int upper,lower;
        private int upperLimit;

        public ServiceParameter(int upper, int lower, int upperLimit) {
            this.upper = upper;
            this.lower = lower;
            this.upperLimit = upperLimit;
        }
    }
}
