package app.OtherSchedule.implementation;

import app.OtherSchedule.AbstractHeart;
import app.OtherSchedule.AbstractMethod;
import app.OtherSchedule.AbstractService;
import app.feignclient.AbstractionService;
import app.feignclient.RancherAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */
@Component
public class Heart extends AbstractHeart{
    @Autowired
    RancherAdapter rancherAdapter;

    @Override
    protected List<AbstractMethod> getStrategyMethods(AbstractService service) {
        return null;
    }

    @Override
    protected List<AbstractService> getAllStrategyService() {
        List<AbstractionService> abstractionServices = rancherAdapter.getServices();
        List<AbstractService> abstractServices = new LinkedList<AbstractService>();
        for(AbstractionService service:abstractionServices){
            abstractServices.add(AbstractionService.change(service));
        }
        return abstractServices;
    }

}

