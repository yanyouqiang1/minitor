package app.feignclient;

import app.OtherSchedule.AbstractService;
import app.OtherSchedule.implementation.Service;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Data
public class AbstractionService {
    private String name;
    private String serviceId;
    private int scale;
    private List<String> containerNames = new LinkedList<String>();
    private List<AbstractionService> linkedServices = new LinkedList<AbstractionService>();

    public static AbstractService change(AbstractionService service){
        AbstractService abstractService = new Service();
        abstractService.setName(service.getName());
        abstractService.setScale(service.getScale());
        return abstractService;
    }

}
