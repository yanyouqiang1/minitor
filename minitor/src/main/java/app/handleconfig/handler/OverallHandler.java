package app.handleconfig.handler;

import app.handle.commonHandle.warehouse.AbstractOverallStatistics;
import app.handle.commonHandle.warehouse.OverallStatisticsHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
public class OverallHandler implements OverallStatisticsHandler {
    @Override
    public void handle(AbstractOverallStatistics overallStatistics) {
        System.out.println("处理Overall");
    }
}
