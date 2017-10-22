package app.database.service;

import app.database.dao.MethodRepository;
import app.database.dao.ServiceRepository;
import app.database.domain.Monitor_method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Component
public class MonitorMethodService {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    MethodRepository methodRepository;

    public List<Monitor_method> getMethodsByServiceName(String serviceName) {
        long serviceID = serviceRepository.findDistinctIdByName(serviceName);
        List<String> names = methodRepository.findDistinctNameByServiceid(serviceID);
        List<Monitor_method> monitor_methods = new LinkedList<>();
        for (String name : names) {
            Monitor_method monitor_method = new Monitor_method();
            monitor_method.setName(name);
            monitor_methods.add(monitor_method);
        }
        return monitor_methods;
    }

    public long getMethodLatestVisitor(String serviceName, String methodName) {
        long serviceID = serviceRepository.findDistinctIdByName(serviceName);
        return methodRepository.findFirstRequest_visitorsByServiceidAndNameOrderByColumnidDesc(serviceID, methodName);
    }

    public int[] getMethodRecentResponseTime(String serviceName, String methodName) {
        long serviceID = serviceRepository.findDistinctIdByName(serviceName);
        List<Monitor_method> methods = methodRepository.findTop90ByServiceidAndNameOrderByColumnidDesc(serviceID,methodName);
        int[] result = new int[methods.size()];
        for(int i=0;i<result.length;i++){
            result[i] = methods.get(i).getResponse_avg();
        }
        return result;
    }
}
