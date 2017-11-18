package app.handleconfig.analyisis;

import app.handle.commonHandle.GlobalResource;
import app.database.dao.ServiceRepository;
import app.database.domain.Monitor_services;
import app.handle.commonHandle.warehouse.statistics.AbstractServiceStatistics;
import entitylib.ResponseMessage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
@Component
@Scope("prototype")
public class ServiceStatistics extends AbstractServiceStatistics {
    protected int response_avg;

    protected Map<Long, MethodCount> methodResponseMap = new HashMap<>();

    @Override
    public void update(ResponseMessage responseMessage) {
        int responsetime = responseMessage.getResponseTime();
        long methodId = responseMessage.getMethodId();
        MethodCount methodCount = findOrCreate(methodId);
        if (responseMessage.getHttpStatus() == 500) {
            methodCount.add(Const.MAX_RESPONSE);
        } else {
            methodCount.add(responsetime);
        }
//        response_avg = (int) (((response_visitors - 1) * response_avg + responsetime) / response_visitors);
    }

    private MethodCount findOrCreate(long methodId) {
        if (methodResponseMap.keySet().contains(methodId)) {
            return methodResponseMap.get(methodId);
        } else {
            MethodCount methodCount = new MethodCount();
            methodResponseMap.put(methodId, methodCount);
            return methodCount;
        }
    }

    @Override
    public void attributeClear() {
        methodResponseMap.clear();
        response_avg = 0;
    }

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public void handleResult(AbstractServiceStatistics service) {
        response_avg = calculate();
        Monitor_services monitor_services = new Monitor_services();
        monitor_services.setId(service.getId());
        monitor_services.setName(service.getName());
        monitor_services.setRequest_visitors(service.getRequest_visitors());
        monitor_services.setResponse_visitors(service.getResponse_visitors());
        monitor_services.setResponse_avg(response_avg);
        monitor_services.setOverall(GlobalResource.getCurrentOverall());
        serviceRepository.save(monitor_services);
    }

    private int calculate() {
        int responseTime = 0;
        for (Map.Entry<Long, MethodCount> entry : methodResponseMap.entrySet()) {
            responseTime += entry.getValue().avg;
        }
        return responseTime;
    }


    @Data
    class MethodCount {
        private int count;
        private int avg;

        public void add(int responseTime) {
            avg = (avg * count + responseTime) / (count + 1);
            count++;
        }
    }

}
