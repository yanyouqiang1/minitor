package app.handleconfig.handler;

import app.handle.commonHandle.warehouse.AbstractMethodStatistics;
import app.handle.commonHandle.warehouse.MethodStatisticsHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
public class MethodHandler implements MethodStatisticsHandler {
    @Override
    public void handle(AbstractMethodStatistics methodStatistics) {
        System.out.println("处理方法");
    }
}
