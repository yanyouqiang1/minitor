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
        if (services != null) {
            return services.getResponse_visitors();
        } else
            return 0;
    }

    public int[] getServiceRecentResponseTime(String serviceName) {
        List<Monitor_services> servicesList = serviceRepository.findTop30ByNameOrderByColumnidDesc(serviceName);
        if (servicesList != null) {
            int size = servicesList.size();
            int[] ints = new int[size];
            for (int i = 0; i < ints.length; i++) {
                ints[size-1-i] = servicesList.get(i).getResponse_avg();
            }
            return ints;
        }else
            return new int[0];
    }

    public Long[][] getServiceGatewayVisitCount(){
        Long[][] ycount = new Long[12][4];
        List<Monitor_services> services = serviceRepository.findTop12ByNameOrderByColumnidDesc("ServiceA");
        int i=0;
        for (Monitor_services services1:services){
            ycount[i++][0] = services1.getRequest_visitors();
        }
        services = serviceRepository.findTop12ByNameOrderByColumnidDesc("ServiceB");
        i=0;
        for (Monitor_services services1:services){
            ycount[i++][1] = services1.getRequest_visitors();
        }
        services = serviceRepository.findTop12ByNameOrderByColumnidDesc("ServiceC");
        i=0;
        for (Monitor_services services1:services){
            ycount[i++][2] = services1.getRequest_visitors();
        }
        services = serviceRepository.findTop12ByNameOrderByColumnidDesc("ServiceD");
        i=0;
        for (Monitor_services services1:services){
            ycount[i++][3] = services1.getRequest_visitors();
        }
        return ycount;
    }
}
