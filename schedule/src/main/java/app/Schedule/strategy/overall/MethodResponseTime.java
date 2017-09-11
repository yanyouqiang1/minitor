package app.Schedule.strategy.overall;

import app.Schedule.AbstractMethod;
import app.Schedule.AbstractService;
import app.Schedule.OverallStrategyInter;
import app.Schedule.StrategyOverallResult;
import app.feignclient.monitor.Monitor;
import app.feignclient.targetAdapter.Adapter;
import app.feignclient.targetAdapter.AdapterService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/7.
 */
@Data
public class MethodResponseTime implements OverallStrategyInter {
    private Map<String,MethodParameter> parameterMap;

    public MethodResponseTime(Map<String, MethodParameter> parameterMap) {
        this.parameterMap = parameterMap;
    }

    @Autowired
    Monitor monitor;

    @Autowired
    Adapter adapter;

    @Override
    public StrategyOverallResult doStrategy(List<AbstractService> serviceList) {
        StrategyOverallResult strategyOverallResult = new StrategyOverallResult();
        for(AbstractService abstractService:serviceList){
            switch (serviceJudge(abstractService)){
                case OVERLOAD:
                    strategyOverallResult.getUpList().add(abstractService);
                    break;
                case RELAX:
                    strategyOverallResult.getDownList().add(abstractService);
                    break;
                case NORMAL:
                    strategyOverallResult.getNormalList().add(abstractService);
                    break;
            }
        }
        simplifyOverloadList(strategyOverallResult.getUpList());
        return strategyOverallResult;
    }

    private final int OVERLOAD =1,RELAX=0,NORMAL=-1;
    //判断服务是否负载
    private int serviceJudge(AbstractService abstractService) {
        List<AbstractMethod> methods = abstractService.getMethodList();
        for(AbstractMethod method:methods){
            int[] responseTimes = monitor.getMethodRecentResponseTime(method.getName());
            if(JudgmentOverLoadAlgorithm(parameterMap.get(method.getName()),responseTimes)){
                return OVERLOAD;
            }else if(JudgmentRelaxAlgorithm(parameterMap.get(method.getName()),responseTimes)){
                return RELAX;
            }else{
                return NORMAL;
            }
        }
        return NORMAL;
    }
    //负载判断算法
    private boolean JudgmentOverLoadAlgorithm(MethodParameter methodParameter, int[] responseTimes) {
        if(methodParameter==null){
            return false;
        }
        int count = 0;
        for(int i=0;i<responseTimes.length;i++){
            if(responseTimes[i]>=methodParameter.getUpper()){
                count++;
            }
        }
        if(count>responseTimes.length*2/3){
            return true;
        }
        return false;
    }
    //低负载判断算法
    private boolean JudgmentRelaxAlgorithm(MethodParameter methodParameter, int[] responseTimes) {
        if(methodParameter==null){
            return false;
        }
        int count = 0;
        for(int i=0;i<responseTimes.length;i++){
            if(responseTimes[i]<=methodParameter.getLower()){
                count++;
            }
        }
        if(count>responseTimes.length*2/3){
            return true;
        }
        return false;
    }

    //简化负载列表
    private void simplifyOverloadList(List<AbstractService> upList) {
        List<AdapterService> adapterServiceList = new LinkedList<AdapterService>();
        for(AbstractService abstractService:upList){
            adapterServiceList.add(AdapterService.generate(abstractService));
        }
        adapterServiceList = adapter.simplifyOverloadList(adapterServiceList);

        upList.clear();
        for(AdapterService adapterService:adapterServiceList){
            upList.add(AbstractService.generate(adapterService));
        }
    }

    @Data
    public static class MethodParameter{
        private int upper,lower;
        private int upperLimit;

        public MethodParameter(int upper, int lower, int upperLimit) {
            this.upper = upper;
            this.lower = lower;
            this.upperLimit = upperLimit;
        }
    }
}
