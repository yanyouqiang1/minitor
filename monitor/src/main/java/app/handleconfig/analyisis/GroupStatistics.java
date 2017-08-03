package app.handleconfig.analyisis;

import app.handle.commonHandle.GlobalResource;
import app.database.dao.GroupRepository;
import app.database.domain.Monitor_group;
import app.handle.commonHandle.warehouse.statistics.AbstractGroupStatistics;
import entitylib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
@Scope("prototype")
public class GroupStatistics extends AbstractGroupStatistics {
    protected float rate;

    @Override
    public void update(ResponseMessage responseMessage) {

    }
    @Override
    public void attributeClear() {
        rate =0;
    }
    @Autowired
    GroupRepository groupRepository;
    @Override
    public void handleResult(AbstractGroupStatistics group) {
        if(group.getParentOverall().getResponse_visitor()!=0) {
            rate = (float) group.getResponse_visitors() / (group.getParentOverall().getResponse_visitor());
        }
        Monitor_group monitor_group = new Monitor_group();
        monitor_group.setId(group.getId());
        monitor_group.setName(group.getName());
        monitor_group.setRequest_visitors(group.getRequest_visitors());
        monitor_group.setResponse_visitors(group.getResponse_visitors());
        monitor_group.setRate(rate);
        monitor_group.setOverall(GlobalResource.getCurrentOverall());
        groupRepository.save(monitor_group);
    }
}
