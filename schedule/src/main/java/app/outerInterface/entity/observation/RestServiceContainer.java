package app.outerInterface.entity.observation;

import app.database.domain.Strategy_container;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class RestServiceContainer {
    private String name;
    private List<Integer>  scales;

    public RestServiceContainer() {
        scales = new LinkedList<Integer>();
    }

    public static RestServiceContainer generate(String serviceName, List<Strategy_container> containers){
        RestServiceContainer serviceContainer = new RestServiceContainer();
        serviceContainer.setName(serviceName);
        for (Strategy_container container:containers){
            serviceContainer.scales.add(container.getScale());
        }
        return serviceContainer;
    }

}
