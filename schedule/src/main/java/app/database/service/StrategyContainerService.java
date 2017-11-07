package app.database.service;

import app.Schedule.AbstractService;
import app.database.dao.StrategyContainerRepository;
import app.database.domain.Strategy_container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2017/9/19.
 */
@Component
public class StrategyContainerService {
    @Autowired
    StrategyContainerRepository containerRepository;

    public List<Strategy_container> getLatestContainers(String serviceName){
        return containerRepository.findTop90ByServiceNameOrderByIdDesc(serviceName);
    }

    public void save(List<AbstractService> allService){
        Strategy_container container;
        for(AbstractService service:allService){
            container = new Strategy_container();
            container.setServiceName(service.getName());
            container.setScale(service.getScale());
            containerRepository.save(container);
        }
    }
}
