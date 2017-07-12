package app.handleconfig.analyisis;

import app.Global;
import app.database.dao.GroupRepository;
import app.database.domain.Monitor_group;
import app.handle.commonHandle.warehouse.statistics.AbstractGroupStatistics;
import entitylib.MsgEntity;
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
    public void update(MsgEntity msgEntity) {

    }
    @Override
    public void attributeClear() {
        rate =0;
    }
    @Autowired
    GroupRepository groupRepository;
    @Override
    public void handleResult(AbstractGroupStatistics group) {
        if(group.getParentOverall().getVisitors()!=0) {
            rate = (float) group.getVisitors() / (group.getParentOverall().getVisitors());
        }
        Monitor_group monitor_group = new Monitor_group();
        monitor_group.setId(group.getId());
        monitor_group.setName(group.getName());
        monitor_group.setVisitors(group.getVisitors());
        monitor_group.setRate(rate);
        monitor_group.setOverall(Global.CurrentOverall);
        groupRepository.save(monitor_group);
    }
}
