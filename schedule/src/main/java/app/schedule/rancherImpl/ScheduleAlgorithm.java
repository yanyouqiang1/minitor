package app.schedule.rancherImpl;

import app.schedule.ScheduleAlgorithmInter;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
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
        return null;
    }

    @Override
    public List<Service> judgeSourceService(List<Service> overloadServices) {
        return null;
    }

    @Override
    public List<Service> adjustRelaxService(List<Service> relaxService) {
        return null;
    }
}
