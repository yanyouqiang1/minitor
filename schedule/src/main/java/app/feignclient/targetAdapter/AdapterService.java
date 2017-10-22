package app.feignclient.targetAdapter;

import app.Schedule.AbstractService;
import app.Schedule.implementation.Service;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Data
public class AdapterService {
    private String name;
//    private String serviceId;
    private int scale;
    private List<String> containerNames = new LinkedList<String>();
    private List<String> linkedServices = new LinkedList<>();

    public static AdapterService generate(AbstractService abstractService){
        AdapterService adapterService = new AdapterService();
        adapterService.setName(abstractService.getName());
        adapterService.setScale(abstractService.getScale());
        return adapterService;
    }
}
