package app.schedule.rancherImpl;

import app.schedule.ScheduleExecuteInter;
import app.schedule.ScheduleServiceInter;
import app.schedule.entity.Method;
import app.schedule.entity.Service;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Data
public class RancherService {
    private String name;
    private String serviceID;
    private int scale;
    private List<RancherService> linkedServices = new LinkedList<RancherService>();

    @JsonIgnore
    @Autowired
    RancherOS rancherOS;

    public void upService() {
        rancherOS.scaleService(name, true);
        scale++;
        System.out.println("服务:"+name+" 容器增加");
    }


    public void downServcie() {
        if (scale > 1) {
            rancherOS.scaleService(name, false);
        }
        scale--;
        System.out.println("服务:"+name+" 容器减少");
    }

    public static Service generate(RancherService rancherService){
        Service service = new Service();
        service.setName(rancherService.getName());
        return service;
    }
}
