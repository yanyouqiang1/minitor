package app.Schedule.strategy.overall;

import app.Schedule.AbstractService;
import app.Schedule.OverallStrategyInter;
import app.Schedule.StrategyOverallResult;
import app.feignclient.monitor.Monitor;
import app.feignclient.targetAdapter.Adapter;
import app.feignclient.targetAdapter.AdapterService;
import app.feignclient.targetAdapter.SimplifyService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/7.
 */
@Data
@Component
@Scope("prototype")
public class NativeStrategy implements OverallStrategyInter {
    public static String name ="Native strategy";

    private Map<String,ServiceParameter> parameterMap;

    public NativeStrategy() {
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
        int[] serviceResponse = monitor.getServiceRecentResponseTime(abstractService.getName());
        if(serviceResponse!=null){
            if(JudgmentOverLoadAlgorithm(parameterMap.get(abstractService.getName()),serviceResponse))
                return OVERLOAD;
            else if(JudgmentRelaxAlgorithm(parameterMap.get(abstractService.getName()),serviceResponse))
                return RELAX;
            else
                return NORMAL;
        }
        return NORMAL;
    }
    //负载判断算法
    private boolean JudgmentOverLoadAlgorithm(ServiceParameter serviceParameter, int[] responseTimes) {
        if(serviceParameter ==null){
            return false;
        }
        int count = 0;
        for(int i=0;i<responseTimes.length;i++){
            if(responseTimes[i]>= serviceParameter.getUpper()){
                count++;
            }
        }
        if(count>responseTimes.length*1/2){
            return true;
        }
        return false;
    }
    //低负载判断算法
    private boolean JudgmentRelaxAlgorithm(ServiceParameter serviceParameter, int[] responseTimes) {
        if(serviceParameter ==null){
            return false;
        }
        int count = 0;
        for(int i=0;i<responseTimes.length;i++){
            if(responseTimes[i]<= serviceParameter.getLower()){
                count++;
            }
        }
        if(count>responseTimes.length*1/2){
            return true;
        }
        return false;
    }

    //简化负载列表
    private void simplifyOverloadList(List<AbstractService> upList) {
        SimplifyService simplifyService= new SimplifyService();
        LinkedList<AdapterService> adapterServiceList = new LinkedList<AdapterService>();
        for(AbstractService abstractService:upList){
            adapterServiceList.add(AdapterService.generate(abstractService));
        }
        simplifyService.setAdapterServices(adapterServiceList);

        simplifyService = adapter.simplifyOverloadList(simplifyService);

        upList.clear();
        for(AdapterService adapterService:simplifyService.getAdapterServices()){
            upList.add(AbstractService.generate(adapterService));
        }
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
