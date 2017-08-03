package app.handleconfig.analyisis;

import app.handle.commonHandle.GlobalResource;
import app.database.dao.ServiceRepository;
import app.database.domain.Monitor_services;
import app.handle.commonHandle.warehouse.statistics.AbstractServiceStatistics;
import entitylib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/11.
 */
@Component
@Scope("prototype")
public class ServiceStatistics extends AbstractServiceStatistics {
    protected int response_min,response_max,response_avg;

    @Override
    public void update(ResponseMessage responseMessage) {
        int responsetime = responseMessage.getResponseTime();
        if(response_visitors ==1){
            response_min = responsetime;
        }
        if(responsetime<response_min){
            response_min = responsetime;
        }
        if(responsetime>response_max){
            response_max = responsetime;
        }

        response_avg = (int) (((response_visitors - 1) * response_avg + responsetime) / response_visitors);
    }

    @Override
    public void attributeClear() {
        response_min=0;
        response_max=0;
        response_avg=0;
    }

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public void handleResult(AbstractServiceStatistics service) {
        Monitor_services monitor_services = new Monitor_services();
        monitor_services.setId(service.getId());
        monitor_services.setName(service.getName());
        monitor_services.setRequest_visitors(service.getRequest_visitors());
        monitor_services.setResponse_visitors(service.getResponse_visitors());
        monitor_services.setResponse_avg(response_avg);
        monitor_services.setResponse_max(response_max);
        monitor_services.setResponse_min(response_min);
        monitor_services.setOverall(GlobalResource.getCurrentOverall());
        serviceRepository.save(monitor_services);
    }


}
