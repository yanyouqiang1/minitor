package app.database.service;

import app.database.dao.MethodRepository;
import app.database.domain.Monitor_method;
import app.handle.commonHandle.warehouse.statistics.gateway.TopologyInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Component
public class MonitorMethodService {
    @Autowired
    MethodRepository methodRepository;

    @Autowired
    TopologyInter topologyInter;
    public long getMethodLatestVisitor(String serviceName, String methodName) {
        Long serviceID=topologyInter.getServiceIDByServiceName(serviceName);
        if(serviceID==null) return 0;
        Monitor_method monitorMethod = methodRepository.findFirstByServiceidAndNameOrderByColumnidDesc(serviceID, methodName);
        return monitorMethod.getRequest_visitors();
    }

    public int[] getMethodRecentResponseTime(String serviceName, String methodName) {
        Long serviceID=topologyInter.getServiceIDByServiceName(serviceName);
        if(serviceID==null) return null;
//        long serviceID = serviceRepository.findDistinctIdByName(serviceName);
        List<Monitor_method> methods = methodRepository.findTop90ByServiceidAndNameOrderByColumnidDesc(serviceID,methodName);
        int[] result = new int[methods.size()];
        for(int i=0;i<result.length;i++){
            result[i] = methods.get(i).getResponse_avg();
        }
        return result;
    }
}
