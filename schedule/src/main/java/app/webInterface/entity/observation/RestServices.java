package app.webInterface.entity.observation;

import app.feignclient.targetAdapter.AdapterService;
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

    public static RestServices generate(List<AdapterService> adapterServices) {
        RestServices restServices = new RestServices();
        for (AdapterService adapterService:adapterServices){
            restServices.getServices().add(Service.generate(adapterService));
        }
        return restServices;
    }

    @Data
    public static class Service {
        private String name;
        private int scale;
        private List<String> linkedService;
        public static Service generate(AdapterService adapterService){
            Service service = new Service();
            service.setName(adapterService.getName());
            service.setScale(adapterService.getScale());
            service.setLinkedService(adapterService.getLinkedServices());
            return service;
        }
    }
}
