package app.database.service;

import app.database.dao.ServiceSumupRepository;
import app.database.domain.Monitor_servicesumup;
import app.innerInterface.forSchedule.VisitorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ServiceSumupService {
    @Autowired
    ServiceSumupRepository sumupRepository;

    public void save(Map<String, Long> serviceMap) {
        for (Map.Entry<String, Long> service : serviceMap.entrySet()) {
            Monitor_servicesumup servicesumup = new Monitor_servicesumup();
            servicesumup.setServiceName(service.getKey());
            servicesumup.setCounts(service.getValue());
            sumupRepository.save(servicesumup);
        }
    }

    @Autowired
    MonitorServiceService serviceService;
    public VisitorEntity getVisitorCount() {
        VisitorEntity visitorEntity = new VisitorEntity();
        Long[][] visitorCount = new Long[12][4];
        List<Monitor_servicesumup> servicesumups = sumupRepository.findTop12ByServiceNameOrderByColumnidDesc("ServiceA");
        int i=0;
        for(Monitor_servicesumup servicesumup:servicesumups){
            visitorCount[i++][0] = servicesumup.getCounts();
        }
        servicesumups = sumupRepository.findTop12ByServiceNameOrderByColumnidDesc("ServiceB");
        i=0;
        for(Monitor_servicesumup servicesumup:servicesumups){
            visitorCount[i++][1] = servicesumup.getCounts();
        }
        servicesumups = sumupRepository.findTop12ByServiceNameOrderByColumnidDesc("ServiceC");
        i=0;
        for(Monitor_servicesumup servicesumup:servicesumups){
            visitorCount[i++][2] = servicesumup.getCounts();
        }
        servicesumups = sumupRepository.findTop12ByServiceNameOrderByColumnidDesc("ServiceD");
        i=0;
        for(Monitor_servicesumup servicesumup:servicesumups){
            visitorCount[i++][3] = servicesumup.getCounts();
        }
        visitorEntity.setV(visitorCount);
        visitorEntity.setY(serviceService.getServiceGatewayVisitCount());
        return visitorEntity;

    }
}
