package app.webInterface;

import app.database.domain.Monitor_method;
import app.database.service.MonitorMethodService;
import app.database.service.MonitorServiceService;
import app.webInterface.forSchedule.Monitor;
import app.webInterface.forSchedule.MonitorMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
public class ScheduleRestControl implements Monitor {
    @Autowired
    MonitorMethodService methodService;

    @Override
    public List<MonitorMethod> getServiceMethods(String serviceName) {
        List<Monitor_method> monitor_methods = methodService.getMethodsByServiceName(serviceName);
        List<MonitorMethod> monitorMethods = new LinkedList<>();
        if(monitor_methods!=null) {
            for (Monitor_method method : monitor_methods) {
                monitorMethods.add(MonitorMethod.generate(method));
            }
        }
        return monitorMethods;
    }

    @Autowired
    MonitorServiceService serviceService;

    @Override
    public long getServiceLatestVisitor(String serviceName) {
        return serviceService.getServiceLatestVisitor(serviceName);
    }

    @Override
    public long getMethodLatestVisitor(String serviceName, String methodName) {
        return methodService.getMethodLatestVisitor(serviceName,methodName);
    }

    @Override
    public int[] getMethodRecentResponseTime(String serviceName, String methodName) {
        return methodService.getMethodRecentResponseTime(serviceName,methodName);
    }

    @Override
    public int[] getServiceRecentResponseTime(String serviceName) {
        return serviceService.getServiceRecentResponseTime(serviceName);
    }
}
