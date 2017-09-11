package app.Schedule;

import app.Schedule.implementation.Service;
import app.feignclient.targetAdapter.AdapterService;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */
@Data
public abstract class AbstractService {
    protected String name;
    protected int scale;
    private List<ServiceSingleStrategyInter> serviceSingleStrategyInterList = new LinkedList<ServiceSingleStrategyInter>();
    private List<AbstractMethod> methodList = new LinkedList<AbstractMethod>();

    //up a service
    public abstract void up();

    //down a service
    public abstract void down();

    public void addStrategy(ServiceSingleStrategyInter strategyInter){
        serviceSingleStrategyInterList.add(strategyInter);
    }

    public static AbstractService generate(AdapterService service){
        AbstractService abstractService = new Service();
        abstractService.setName(service.getName());
        abstractService.setScale(service.getScale());
        return abstractService;
    }
}
