package app.outerInterface.entity.strategy;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Overview {
    Status timeWindow = new Status();

    List<StrategyService> strategyServices= new LinkedList<>();

    List<Status> strategyMethods = new LinkedList<>();

    @Data
    public static class StrategyService{
        String name;
        List<Status> strategys= new LinkedList<>();

    }

    @Data
    public static class Status {
        String name;
        boolean status;
    }
}
