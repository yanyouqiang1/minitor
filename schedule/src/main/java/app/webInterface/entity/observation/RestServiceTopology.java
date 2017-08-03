package app.webInterface.entity.observation;

import app.schedule.rancherImpl.RancherService;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class RestServiceTopology {
    private List<Service> serviceTopology;
    public RestServiceTopology(){
        serviceTopology = new LinkedList<Service>();
    }
    public static RestServiceTopology generate(List<RancherService> rancherServices){
        RestServiceTopology restServiceTopology = new RestServiceTopology();
        for(RancherService s:rancherServices){
            restServiceTopology.getServiceTopology().add(Service.generate(s));
        }
        return  restServiceTopology;
    }
    @Data
    public static class Service{
        private String name;
        private String id;
        private List<String> linkService;
        private List<String> containerNames;
        public Service() {
            linkService = new LinkedList<String>();
            containerNames = new LinkedList<String>();
        }

        public static Service generate(RancherService rancherService){
            Service service = new Service();
            service.setName(rancherService.getName());
            service.setId(rancherService.getServiceId());
            for(RancherService s:rancherService.getLinkedServices()){
                service.getLinkService().add(s.getName());
            }
            for(String containerName:rancherService.getContainerNames()){
                service.getContainerNames().add(containerName);
            }
            return service;
        }
    }
}
