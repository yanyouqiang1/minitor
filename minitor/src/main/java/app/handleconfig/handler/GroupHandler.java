package app.handleconfig.handler;

import app.handle.commonHandle.warehouse.AbstractGroupStatistics;
import app.handle.commonHandle.warehouse.GroupStatisticsHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
public class GroupHandler implements GroupStatisticsHandler {
    @Override
    public void handle(AbstractGroupStatistics groupStatistics) {
        System.out.println("处理group");
    }
}
