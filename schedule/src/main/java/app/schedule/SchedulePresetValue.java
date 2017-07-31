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

    public void setOrUpdateCondition(MethodCondition methodCondition){
        for(MethodCondition mc:methodConditions){
            if(mc.getMethodId()==methodCondition.getMethodId()){
                mc.setResponseThreshold(methodCondition.getResponseThreshold());
                mc.setMethodName(methodCondition.getMethodName());
                System.out.println("更新id:"+methodCondition.getMethodId()+"methodCondition操作");
                return;
            }
        }
        methodConditions.add(methodCondition);
        System.out.println("添加id:"+methodCondition.getMethodId()+"methodCondition操作");
    }

    public int getMethodThreshold(long methodId){
        for(MethodCondition methodCondition:methodConditions){
            if(methodCondition.getMethodId()==methodId){
                return methodCondition.getResponseThreshold();
            }
        }
        System.out.println("没有找到方法id："+methodId+"的阈值设定,是否忘记添加");
        return -1;
    }
    @Data
    @NoArgsConstructor
    public static class MethodCondition{
        private Long methodId;
        private String methodName;
        private int responseThreshold;
    }
}
