package app.handleconfig.handler;

import app.database.domain.Mini_group;
import app.database.service.KeeptoSave;
import app.handle.commonHandle.warehouse.AbstractGroupStatistics;
import app.handle.commonHandle.warehouse.GroupStatisticsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
public class GroupHandler implements GroupStatisticsHandler {
    @Autowired
    KeeptoSave keeptoSave;
    @Override
    public void handle(AbstractGroupStatistics groupStatistics) {
        Mini_group group = new Mini_group();
        group.setGroupName(groupStatistics.getGroupName());
        group.setVisitors(groupStatistics.getVisitors());
        group.setName(groupStatistics.getName());
        group.setPopularname(groupStatistics.getPopularName());

        //rate
        long visitors = groupStatistics.getParentOverall().getVisitors();
        group.setRate((float) (groupStatistics.getVisitors()/visitors));

        keeptoSave.save(group);
    }
}
