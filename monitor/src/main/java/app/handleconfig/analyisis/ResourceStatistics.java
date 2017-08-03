package app.handleconfig.analyisis;

import app.handle.commonHandle.GlobalResource;
import app.database.dao.ResourceRepository;
import app.database.domain.Monitor_resource;
import app.handle.commonHandle.warehouse.statistics.AbstractResourceStatistics;
import entitylib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/11.
 */
@Component
@Scope("prototype")
public class ResourceStatistics extends AbstractResourceStatistics {
    @Override
    public void update(ResponseMessage responseMessage) {

    }
    @Override
    public void attributeClear() {

    }
    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public void handleResult(AbstractResourceStatistics resource) {
        Monitor_resource monitor_resource = new Monitor_resource();
        monitor_resource.setId(resource.getId());
        monitor_resource.setName(resource.getName());
        monitor_resource.setRequest_visitors(resource.getRequest_visitors());
        monitor_resource.setResponse_visitors(resource.getResponse_visitors());
        monitor_resource.setGroupId(resource.getParentGroup().getId());
        monitor_resource.setOverall(GlobalResource.getCurrentOverall());
        resourceRepository.save(monitor_resource);
    }


}
