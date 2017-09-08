package app.schedule.rancherImpl.algorithm;

import app.schedule.AbstractScheduleAlgorithm;
import app.schedule.SchedulePresetValue;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
import app.schedule.rancherImpl.RancherStack;
import app.schedule.entity.ConcreteData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Component
public class ScheduleAlgorithm extends AbstractScheduleAlgorithm {
    @Override
    protected boolean isOverload(Service service, List<Method> methodList, SchedulePresetValue presetValue) {
        boolean overload = false;
        long count = 0;
        for(Method method:methodList){
            if(method.getResponse_avg()>presetValue.getMethodUpperThreshold(method.getId())){
                count++;
            }
        }
        if(count>methodList.size()/2){
            overload = true;
        }
        return overload;
    }

    @Override
    protected boolean isRelax(Service service, List<Method> methodList, SchedulePresetValue presetValue) {
        boolean relax = false;
        long count = 0;
        for(Method method:methodList){
            if(method.getResponse_avg()<presetValue.getMethodDownThreshold(method.getId())){
                count++;
            }
        }
        if(count>methodList.size()/2){
            relax = true;
        }
        return relax;
    }

    @Autowired
    RancherStack rancherStack;
    @Override
    protected List<Service> findSourceOverloadService(List<ConcreteData> overloadConcrete) {
        System.out.println("超载列表");
        for(ConcreteData concreteData:overloadConcrete){
            System.out.println(concreteData.getService().getName()+" ");
        }
        System.out.println("正在寻找根源问题:"+System.currentTimeMillis());

        List<Service> overloadServices=new LinkedList<Service>();
        for(ConcreteData concreteData:overloadConcrete){
            overloadServices.add(concreteData.getService());
        }

        int[][] topology = rancherStack.getTopology();
        //转化
        int[] overIndex = new int[overloadServices.size()];
        for(int i=0;i<overIndex.length;i++){
            Service service = overloadServices.get(i);
            overIndex[i] = rancherStack.getIndexOfRancherService(service);
        }
        for(int i=0;i<overIndex.length;i++){
            for(int j=0;j<overIndex.length;j++){
                if(overIndex[i]==-1) continue;
                if(topology[i][j]==1){
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

        return overloadServices;
    }

    @Override
    protected List<Service> findRelaxService(List<ConcreteData> relaxConcrete) {
        System.out.println("轻载列表");
        for(ConcreteData concreteData:relaxConcrete){
            System.out.println(concreteData.getService().getName()+" ");
        }
        System.out.println("正在调度:"+System.currentTimeMillis());


        return new LinkedList<Service>();
    }

    //    @Autowired
//    SchedulePresetValue schedulePresetValue;
//
//    //具体的超载/轻松方法
//    List<ConcreteData> overloadServiceList,relaxServiceList;
//
//    public ScheduleAlgorithm(){
//        overloadServiceList = new LinkedList<ConcreteData>();
//        relaxServiceList = new LinkedList<ConcreteData>();
//    }
//
//    @Override
//    public Boolean isServiceOverload(AbstractService service, List<List<Method>> methods) {
//        for(List<Method> singleMethod:methods){
//            // singleMethod 判断是否负载过重
//            //当前默认有一半超过400ms时 断定负载过重
//            int size = singleMethod.size();
//            int count = 0;
//            for(Method m:singleMethod){
//                if(m.getResponse_avg()>schedulePresetValue.getMethodThreshold(m.getId())){
//                    count++;
//                }
//            }
//            if(count>size/2){
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    @Override
//    public Boolean isServiceRelax(AbstractService service, List<List<Method>> methods) {
//        for(List<Method> singleMethod:methods){
//            // singleMethod 判断是否负载过重
//            //当前默认有一半低于200ms时  断定负载轻松
//            int size = singleMethod.size();
//            int count = 0;
//            for(Method m:singleMethod){
//                if(m.getResponse_avg()<200){
//                    count++;
//                }
//            }
//            if(count>size/2){
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    @Autowired
//    RancherStack rancherStack;
//
//    @Override
//    public List<AbstractService> judgeSourceService(List<AbstractService> overloadServices) {
//        int[][] toplogy = rancherStack.getTopology();
//
//        //转化
//        int[] overIndex = new int[overloadServices.size()];
//        for(int i=0;i<overIndex.length;i++){
//            AbstractService service = overloadServices.get(i);
//            overIndex[i] = rancherStack.getIndexOfRancherService(service);
//        }
//
//        for(int i=0;i<overIndex.length;i++){
//            for(int j=0;j<overIndex.length;j++){
//                if(overIndex[i]==-1) continue;
//                if(toplogy[i][j]==1){
//                    overIndex[i]=-1;
//                }
//
//            }
//        }
//        //转回
//        for(int i=0;i<overIndex.length;i++){
//            if(overIndex[i]==-1){
//                overloadServices.remove(overloadServices.indexOf(i));
//            }
//        }
//        System.out.println("进行负载判断---:"+overloadServices.toString());
//        return overloadServices;
//    }
//
//    @Override
//    public List<AbstractService> adjustRelaxService(List<AbstractService> relaxService) {
//        return relaxService;
//    }


}
