package app.schedule;

import app.schedule.entity.ConcreteData;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public abstract class AbstractScheduleAlgorithm implements ScheduleAlgorithmInter{
    @Getter
    @Autowired
    SchedulePresetValue schedulePresetValue;

    @Override
    public ConcreteData fullfillDate(Service service, List<List<Method>> methods) {
        ConcreteData concreteData = new ConcreteData(service);
        for(List<Method> methodList:methods){
            ConcreteData.BrieflyMethod brieflyMethod = new ConcreteData.BrieflyMethod();
            brieflyMethod.setName(methodList.get(0).getName());
            brieflyMethod.setId(methodList.get(0).getId());
            //判断服务方法是否高负载
            if(isOverload(service,methodList,schedulePresetValue)){
                concreteData.getMethodOverloadList().add(brieflyMethod);
            }else if(isRelax(service,methodList,schedulePresetValue)){
                concreteData.getMethodRelaxList().add(brieflyMethod);
            }else{
                concreteData.getMethodNormal().add(brieflyMethod);
            }
        }
        return concreteData;
    }
    protected abstract boolean isOverload(Service service,List<Method> methodList,SchedulePresetValue presetValue);
    protected abstract boolean isRelax(Service service,List<Method> methodList,SchedulePresetValue presetValue);
    @Override
    public List<Service> judgeUpService(List<ConcreteData> concreteDataList) {
        List<ConcreteData> overloadConcrete = new ArrayList<ConcreteData>();
        for(ConcreteData concreteData:concreteDataList){
            if(!concreteData.getMethodOverloadList().isEmpty()){
                overloadConcrete.add(concreteData);
            }
        }
        List<Service> upService = findSourceOverloadService(overloadConcrete);
        return upService;
    }

    protected abstract List<Service> findSourceOverloadService(List<ConcreteData> overloadConcrete);

    @Override
    public List<Service> judgeDownService(List<ConcreteData> concreteDataList) {
        List<ConcreteData> relaxConcrete = new ArrayList<ConcreteData>();
        for(ConcreteData concreteData:concreteDataList){
            if(!concreteData.getMethodRelaxList().isEmpty()){
                relaxConcrete.add(concreteData);
            }
        }
        List<Service> downService = findRelaxService(relaxConcrete);
        return downService;
    }

    protected abstract List<Service> findRelaxService(List<ConcreteData> relaxConcrete);
}
