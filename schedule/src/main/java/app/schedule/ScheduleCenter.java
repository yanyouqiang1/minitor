package app.schedule;

import app.schedule.entity.Method;
import app.schedule.entity.Service;
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
    @Autowired
    ScheduleServiceInter scheduleService;

    @Autowired
    ScheduleAlgorithmInter scheduleAlgorithm;

    @Autowired
    ScheduleExecuteInter scheduleExecute;

    @Scheduled(fixedRate = 10000)
    public void doSchedule(){
        List<Service> overLoads = new LinkedList<Service>(); //超载列表
        List<Service> relaxs = new LinkedList<Service>(); //轻载列表

        //判断服务负载高还是轻
        List<Service> services = scheduleService.getAllServcie();
        for (Service s:services){
            List<List<Method>> methods = scheduleService.getServiceMethod(s);
            if(scheduleAlgorithm.isServiceOverload(s,methods)){
                overLoads.add(s);
            }else if(scheduleAlgorithm.isServiceRelax(s,methods)){
                relaxs.add(s);
            }
        }
        //寻找根源 执行调度
        List<Service> adjustUP = scheduleAlgorithm.judgeSourceService(overLoads);
        for(Service sup:adjustUP){
            scheduleExecute.upService(sup);
        }
        //调整轻松服务
        List<Service> adjustDOWN = scheduleAlgorithm.adjustRelaxService(relaxs);
        for(Service sdown:adjustDOWN){
            scheduleExecute.downService(sdown);
        }
        scheduleExecute.afterSchedule();
    }
}
