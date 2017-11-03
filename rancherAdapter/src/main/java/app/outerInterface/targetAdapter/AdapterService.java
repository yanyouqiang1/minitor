package app.outerInterface.targetAdapter;

import app.rancher.RancherService;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22.
 */
@Data
public class AdapterService {
    private String name;
    private int scale;
    private List<String> containerNames = new LinkedList<String>();
    private List<String> linkedServices = new LinkedList<>();

    public static AdapterService generate(RancherService rancherService){
        AdapterService adapterService = new AdapterService();
        adapterService.setName(rancherService.getName());
        adapterService.setScale(rancherService.getScale());
        adapterService.setContainerNames(rancherService.getContainerNames());
        for(RancherService rancherService1:rancherService.getLinkedServices()){
            adapterService.getLinkedServices().add(rancherService1.getName());
        }
        return adapterService;
    }
}
