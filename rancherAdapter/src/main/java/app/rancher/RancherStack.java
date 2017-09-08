package app.rancher;

import app.rancher.entity.ResultData;
import lombok.Data;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
@Component
@Data
public class RancherStack {
    private List<RancherService> rancherServices = new LinkedList<RancherService>();


    @Autowired
    RancherOS rancherOS;

    @PostConstruct
    public void init() {
        try{
            List<String> serviceIds = rancherOS.getStackServices();
            for(String serviceId:serviceIds){
                RancherService service = findServiceById(serviceId);
                if(service==null){
                    service = new RancherService();
                    service.setServiceId(serviceId);
                    rancherServices.add(service);
                }
                service.setName(rancherOS.getServiceName(serviceId));
                service.setScale(rancherOS.getServiceScale(serviceId));
                service.setContainerNames(rancherOS.getServiceContainerNames(serviceId));
                List<String> linkedServices = rancherOS.getLinkedServices(serviceId);
                for(String ls:linkedServices){
                    RancherService ss = findServiceById(ls);
                    if(ss==null){
                        ss = new RancherService();
                        ss.setServiceId(ls);
                        rancherServices.add(ss);
                    }
                    service.getLinkedServices().add(ss);
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    private RancherService findServiceById(String serviceId) {
        for(RancherService s:rancherServices){
            if(s.getServiceId().equals(serviceId)){
                return s;
            }
        }
        return null;
    }

    @Override
    public void upService(Service service) {
        RancherService rancherService = findServiceById(service.getName());
        if(rancherService!=null){
            rancherService.upService();
        }
    }

    @Override
    public void downService(Service service) {
        RancherService rancherService = findServiceById(service.getName());
        if(rancherService!=null){
            rancherService.downServcie();
        }
    }

    @Autowired
    MethodService methodService;
    @Override
    public List<List<Method>> getServiceMethod(Service service) {
        return methodService.getServiceMethod(service.getName());
    }

    @Override
    public List<Service> getAllService() {
        List<Service> services = new LinkedList<Service>();
        for(RancherService rs:rancherServices){
            Service service = RancherService.generate(rs);
            services.add(service);
        }
        return services;
    }

    @Autowired
    ScheduleService scheduleService;

    @Override
    public void afterSchedule() {
        scheduleService.save(this.rancherServices);
    }

   //服务拓扑图
    public int[][] getTopology(){
        int[][] topology = new int[rancherServices.size()][rancherServices.size()];
        for(int i=0;i<rancherServices.size();i++){
            RancherService rs = rancherServices.get(i);
            List<RancherService> linkService = rs.getLinkedServices();
            if(!linkService.isEmpty()){
                for(RancherService s:linkService){
                    topology[i][rancherServices.indexOf(s)]=1;
                }
            }
        }
        return topology;
    }

    public int getIndexOfRancherService(Service service){
        String serviceName = service.getName();
        for(RancherService rancherService:rancherServices){
            if(rancherService.getName().equals(serviceName)){
                return rancherServices.indexOf(rancherService);
            }
        }
        return -1;
    }

    public RancherService getServiceByName(String rancherServiceName){
        for(RancherService s:rancherServices){
            if(s.getName().equals(rancherServiceName)){
                return s;
            }
        }
        return null;
    }
    //获得某个容器状态
    @Autowired
    ContainerInter containerInter;
    public ResultData getContainerCpuStatusByName(String containerName){
        return containerInter.getCpuRateByContainerName(containerName);
    }
    public ResultData getContainerMemoryStatusByName(String containerName){
        return containerInter.getMemoryByContainerName(containerName);
    }
}
