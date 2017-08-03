package app.schedule;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Component
public class SchedulePresetValue {

    private List<MethodCondition> methodConditions;

    public SchedulePresetValue(){
        methodConditions = new LinkedList<MethodCondition>();
    }


    public void setMethodUpperThreshold(long methodId,int upperThreshold){
        for(MethodCondition mc:methodConditions){
            if(mc.getMethodId()==methodId){
                mc.setResponseUpperThreshold(upperThreshold);
            }
        }
        MethodCondition methodCondition = new MethodCondition();
        methodCondition.setMethodId(methodId);
        methodCondition.setResponseUpperThreshold(upperThreshold);
        methodConditions.add(methodCondition);
    }

    public void setMethodLowerThreshold(long methodId,int lowerThreshold){
        for(MethodCondition mc:methodConditions){
            if(mc.getMethodId()==methodId){
                mc.setResponseUpperThreshold(lowerThreshold);
            }
        }
        MethodCondition methodCondition = new MethodCondition();
        methodCondition.setMethodId(methodId);
        methodCondition.setResponseLowerTHreashold(lowerThreshold);
        methodConditions.add(methodCondition);
    }

    public int getMethodUpperThreshold(long methodId){
        for(MethodCondition methodCondition:methodConditions){
            if(methodCondition.getMethodId()==methodId){
                return methodCondition.getResponseUpperThreshold();
            }
        }
        System.out.println("没有找到方法id："+methodId+"的上阈值设定,是否忘记添加");
        return Integer.MAX_VALUE;
    }
    public int getMethodDownThreshold(long methodId){
        for(MethodCondition methodCondition:methodConditions){
            if(methodCondition.getMethodId()==methodId){
                return methodCondition.getResponseLowerTHreashold();
            }
        }
        System.out.println("没有找到方法id："+methodId+"的下阈值设定,是否忘记添加");
        return -1;
    }



    @Data
    @NoArgsConstructor
    public static class MethodCondition{
        private Long methodId;
        private int responseUpperThreshold;
        private int responseLowerTHreashold;
    }
}
