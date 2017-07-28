package app.webInterface;

import app.containerstate.ContainerInter;
import app.containerstate.prometheus.PrometheusImpl;
import app.database.service.ScheduleService;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
import app.schedule.rancherImpl.RancherStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
@RestController
public class RestControl {

    @Autowired
    RancherStack rancherStack;

    @RequestMapping("/topology")
    public List getTopology(){
        return rancherStack.getRancherServices();
    }

    @RequestMapping("/service/{serviceName}")
    public List<List<Method>> getTopology(@PathVariable(name = "serviceName")String serviceName){
        Service service = new Service();
        service.setName(serviceName);
        return rancherStack.getServiceMethod(service);
    }

    @Autowired
    ScheduleService scheduleService;
    @RequestMapping("/schedule/{serviceName}")
    public List getSchedule(@PathVariable(name = "serviceName")String serviceName){
        return scheduleService.getScheduleByServiceName(serviceName);
    }

    @Autowired
    ContainerInter containerInter;
    @RequestMapping("/test")
    public String test(){
        containerInter.getCpuRateByContainerName("monitor-sender-1");
        return "ok";
    }
}
