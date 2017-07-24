package app.schedule.rancherImpl;

import app.database.domain.Schedule_service;
import app.database.service.MethodService;
import app.database.service.ScheduleService;
import app.schedule.ScheduleExecuteInter;
import app.schedule.ScheduleServiceInter;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
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
public class RancherStack implements ScheduleExecuteInter,ScheduleServiceInter {
    private List<RancherService> rancherServices = new LinkedList<RancherService>();


    @Autowired
    RancherOS rancherOS;

    @PostConstruct
    public void init() {
        try{
            List<String> serviceIds = rancherOS.getStackServices();
            for(String serviceId:serviceIds){
                RancherService service = findService(serviceId);
                if(service==null){
                    service = new RancherService();
                    service.setServiceID(serviceId);
                    rancherServices.add(service);
                }
                service.setName(rancherOS.getServiceName(serviceId));
                service.setScale(rancherOS.getServiceScale(serviceId));
                List<String> linkedServices = rancherOS.getlinkedServices(serviceId);
                for(String ls:linkedServices){
                    RancherService ss = findService(ls);
                    if(ss==null){
                        ss = new RancherService();
                        ss.setServiceID(ls);
                        rancherServices.add(ss);
                    }
                    service.getLinkedServices().add(ss);
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    private RancherService findService(String serviceId) {
        for(RancherService s:rancherServices){
            if(s.getServiceID().equals(serviceId)){
                return s;
            }
        }
        return null;
    }

    @Override
    public void upService(Service service) {
        RancherService rancherService = findService(service.getName());
        if(rancherService!=null){
            rancherService.upService();
        }
    }

    @Override
    public void downService(Service service) {
        RancherService rancherService = findService(service.getName());
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
    public List<Service> getAllServcie() {
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

    /***
     * 服务拓扑图
     * @return
     */
    public int[][] getToplogy(){
        int[][] toplogy = new int[rancherServices.size()][];
        for(int i=0;i<rancherServices.size();i++){
            RancherService rs = rancherServices.get(i);
            List<RancherService> linkService = rs.getLinkedServices();
            if(!linkService.isEmpty()){
                for(RancherService s:linkService){
                    toplogy[i][rancherServices.indexOf(s)]=1;
                }
            }
        }
        return toplogy;
    }

    public int getIndexofRancherService(Service service){
        String serviceName = service.getName();
        for(RancherService rancherService:rancherServices){
            if(rancherService.getName().equals(serviceName)){
                return rancherServices.indexOf(rancherService);
            }
        }
        return -1;
    }
}
