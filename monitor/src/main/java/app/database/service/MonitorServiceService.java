package app.database.service;

import app.database.dao.ServiceRepository;
import app.database.domain.Monitor_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 */
@Component
public class MonitorServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    public long getServiceLatestVisitor(String serviceName) {
        Monitor_services services = serviceRepository.findFirstByNameOrderByColumnidDesc(serviceName);
        if(services!=null) {
            return services.getResponse_visitors();
        }else
            return 0;
    }

    public int[] getServiceRecentResponseTime(String serviceName) {
        List<Monitor_services> servicesList = serviceRepository.findTop30ByNameOrderByColumnid(serviceName);
        int[] ints = new int[servicesList.size()];
        for(int i=0;i<ints.length;i++){
            ints[i] = servicesList.get(i).getResponse_avg();
        }
        return ints;
    }
}
