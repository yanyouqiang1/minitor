package app.outerInterface;

import app.database.service.MonitorMethodService;
import app.database.service.MonitorServiceService;
import app.handle.commonHandle.warehouse.statistics.gateway.TopologyInter;
import app.outerInterface.forSchedule.Monitor;
import app.outerInterface.forSchedule.MonitorMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public class ScheduleRestControl implements Monitor {
    @Autowired
    MonitorMethodService methodService;
    @Autowired
    TopologyInter topologyInter;

    @Override
    public List<MonitorMethod> getServiceMethods(String serviceName) {
//        List<Monitor_method> monitor_methods = methodService.getMethodsByServiceName(serviceName);
//        List<MonitorMethod> monitorMethods = new LinkedList<>();
//        if(monitor_methods!=null) {
//            for (Monitor_method method : monitor_methods) {
//                monitorMethods.add(MonitorMethod.generate(method));
//            }
//        }
//        return monitorMethods;
        List<MonitorMethod> methodList = new LinkedList<>();
        List<String> methodsName = topologyInter.getMethodsByServiceName(serviceName);
        if(methodsName==null){
            return null;
        }
        MonitorMethod monitorMethod;
        for (String m : methodsName) {
            monitorMethod = new MonitorMethod();
            monitorMethod.setName(m);
            methodList.add(monitorMethod);
        }
        return  methodList;
    }

    @Autowired
    MonitorServiceService serviceService;

    @Override
    public long getServiceLatestVisitor(String serviceName) {
        return serviceService.getServiceLatestVisitor(serviceName);
    }

    @Override
    public long getMethodLatestVisitor(String serviceName, String methodName) {
        return methodService.getMethodLatestVisitor(serviceName, methodName);
    }

    @Override
    public int[] getMethodRecentResponseTime(String serviceName, String methodName) {
        return methodService.getMethodRecentResponseTime(serviceName, methodName);
    }

    @Override
    public int[] getServiceRecentResponseTime(String serviceName) {
        return serviceService.getServiceRecentResponseTime(serviceName);
    }
}
