package app.schedule;

import app.schedule.entity.ConcreteData;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
import app.schedule.rancherImpl.RancherStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Component
public class ScheduleCenter {
    List<ConcreteData> concreteServiceList;

    @Autowired
    ScheduleServiceInter scheduleService;

    @Autowired
    ScheduleAlgorithmInter scheduleAlgorithm;

    @Autowired
    ScheduleExecuteInter scheduleExecute;

    @Scheduled(initialDelay = 60*1000,fixedRate = 60*1000)
    public void doSchedule(){
//        List<Service> overLoads = new LinkedList<Service>(); //超载列表
//        List<Service> relaxs = new LinkedList<Service>(); //轻载列表

        //判断服务负载高还是轻
        List<Service> services = scheduleService.getAllService();
        for (Service s:services){
            List<List<Method>> methods = scheduleService.getServiceMethod(s);
            ConcreteData concreteData = scheduleAlgorithm.fullfillDate(s,methods);
            concreteServiceList.add(concreteData);
//            if(scheduleAlgorithm.isServiceOverload(s,methods)){
//                overLoads.add(s);
//            }else if(scheduleAlgorithm.isServiceRelax(s,methods)){
//                relaxs.add(s);
//            }
        }
        List<Service> adjustUP = scheduleAlgorithm.judgeUpService(concreteServiceList);
        //寻找根源 执行调度
//        List<Service> adjustUP = scheduleAlgorithm.judgeSourceService(overLoads);
        for(Service sup:adjustUP){
            scheduleExecute.upService(sup);
            System.out.println("服务："+sup.getName()+"扩大容器");
        }
//        //调整轻松服务
//        List<Service> adjustDOWN = scheduleAlgorithm.adjustRelaxService(relaxs);
        List<Service> adjustDOWN = scheduleAlgorithm.judgeDownService(concreteServiceList);
        for(Service sdown:adjustDOWN){
            scheduleExecute.downService(sdown);
            System.out.println("服务："+sdown.getName()+"缩小容器");
        }
        scheduleExecute.afterSchedule();
        concreteServiceList.clear();
    }
    @Autowired
    RancherStack rancherStack;
    @Scheduled(initialDelay = 90*1000,fixedRate = 90*1000)
    public void refreshRancherStack(){
        rancherStack.init();
        System.out.println("同步rancher,"+System.currentTimeMillis());
    }


    public ScheduleCenter() {
        concreteServiceList = new LinkedList<ConcreteData>();
    }
}
