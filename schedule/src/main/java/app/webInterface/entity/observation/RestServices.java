package app.webInterface.entity.observation;

import app.database.domain.Monitor_services;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class RestServices {
    List<Service> services;

    public RestServices() {
        services = new LinkedList<Service>();
    }

    public static RestServices generate(List<List<Monitor_services>> groupServiceList) {
        RestServices restServices = new RestServices();
        if (groupServiceList != null) {
            for (List<Monitor_services> servicesList : groupServiceList) {
                RestServices.Service service = RestServices.Service.generate(servicesList);
                restServices.getServices().add(service);
            }
        }
        return restServices;
    }

    @Data
    public static class Service {
        private String name;
        private Long id;
        private List<Integer> responseTime;

        public Service() {
            responseTime = new LinkedList<Integer>();
        }

        public static Service generate(List<Monitor_services> monitor_services) {
            Service service = new Service();
            if (!monitor_services.isEmpty()) {
                service.setName(monitor_services.get(0).getName());
                service.setId(monitor_services.get(0).getId());
            }
            for (Monitor_services services : monitor_services) {
                service.getResponseTime().add(services.getResponse_avg());
            }
            return service;
        }
    }
}
