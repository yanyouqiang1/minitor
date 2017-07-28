package app.schedule.rancherImpl.algorithm;

import app.schedule.ScheduleAlgorithmInter;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
import app.schedule.rancherImpl.RancherStack;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Component
public class ScheduleAlgorithm implements ScheduleAlgorithmInter {
    @Override
    public Boolean isServiceOverload(Service service, List<List<Method>> methods) {
        for(List<Method> singleMethod:methods){
            // singleMethod 判断是否负载过重
            //当前默认有一半超过400ms时 断定负载过重
            int size = singleMethod.size();
            int count = 0;
            for(Method m:singleMethod){
                if(m.getResponse_avg()>400){
                    count++;
                }
            }
            if(count>size/2){
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean isServiceRelax(Service service, List<List<Method>> methods) {
        for(List<Method> singleMethod:methods){
            // singleMethod 判断是否负载过重
            //当前默认有一半低于200ms时  断定负载轻松
            int size = singleMethod.size();
            int count = 0;
            for(Method m:singleMethod){
                if(m.getResponse_avg()<200){
                    count++;
                }
            }
            if(count>size/2){
                return true;
            }
        }
        return false;
    }

    @Autowired
    RancherStack rancherStack;

    @Override
    public List<Service> judgeSourceService(List<Service> overloadServices) {
        int[][] toplogy = rancherStack.getToplogy();

        //转化
        int[] overIndex = new int[overloadServices.size()];
        for(int i=0;i<overIndex.length;i++){
            Service service = overloadServices.get(i);
            overIndex[i] = rancherStack.getIndexofRancherService(service);
        }

        for(int i=0;i<overIndex.length;i++){
            for(int j=0;j<overIndex.length;j++){
                if(overIndex[i]==-1) continue;
                if(toplogy[i][j]==1){
                    overIndex[i]=-1;
                }

            }
        }
        //转回
        for(int i=0;i<overIndex.length;i++){
            if(overIndex[i]==-1){
                overloadServices.remove(overloadServices.indexOf(i));
            }
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("增加"+ JSONObject.wrap(overloadServices));

        return overloadServices;
    }

    @Override
    public List<Service> adjustRelaxService(List<Service> relaxService) {
        return relaxService;
    }

}
