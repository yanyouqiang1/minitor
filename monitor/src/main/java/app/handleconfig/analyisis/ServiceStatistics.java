package app.handleconfig.analyisis;

import app.Global;
import app.database.dao.ServiceRepository;
import app.database.domain.Monitor_services;
import app.handle.commonHandle.warehouse.statistics.AbstractServiceStatistics;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/11.
 */
@Component
@Scope("prototype")
public class ServiceStatistics extends AbstractServiceStatistics {
    @Override
    public void update(MsgEntity msgEntity) {
        
    }

    @Override
    public void attributeClear() {

    }

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public void handleResult(AbstractServiceStatistics service) {
        Monitor_services monitor_services = new Monitor_services();
        monitor_services.setId(service.getId());
        monitor_services.setName(service.getName());
        monitor_services.setVisitors(service.getVisitors());
        monitor_services.setOverall(Global.CurrentOverall);
        serviceRepository.save(monitor_services);
    }


}
