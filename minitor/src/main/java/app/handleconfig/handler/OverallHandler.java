package app.handleconfig.handler;

import app.database.domain.Mini_overall;
import app.database.service.KeeptoSave;
import app.handle.commonHandle.warehouse.AbstractOverallStatistics;
import app.handle.commonHandle.warehouse.OverallStatisticsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
public class OverallHandler implements OverallStatisticsHandler {
    @Autowired
    KeeptoSave keeptoSave;
    @Override
    public void handle(AbstractOverallStatistics overallStatistics) {
        Mini_overall overall = new Mini_overall();
        overall.setVisitors(overallStatistics.getVisitors());
        overall.setMethod_delete(overallStatistics.getMethod_delete());
        overall.setMethod_get(overallStatistics.getMethod_get());
        overall.setMethod_post(overallStatistics.getMethod_post());
        overall.setMethod_put(overallStatistics.getMethod_put());
        overall.setResponse_max(overallStatistics.getResponse_max());
        overall.setResponse_min(overallStatistics.getResponse_min());
        overall.setResposne_avg(overallStatistics.getResposne_avg());
        overall.setStatus_100(overallStatistics.getStatus_100());
        overall.setStatus_200(overallStatistics.getStatus_200());
        overall.setStatus_300(overallStatistics.getStatus_300());
        overall.setStatus_400(overallStatistics.getStatus_400());
        overall.setStatus_500(overallStatistics.getStatus_500());

        //rate
        long visitors = overallStatistics.getVisitors();
        if(visitors==0) {
            overall.setRate_method_delete(0f);
            overall.setRate_method_get(0f);
            overall.setRate_method_post(0f);
            overall.setRate_method_put(0f);
            overall.setRate_status_100(0f);
            overall.setRate_status_200(0f);
            overall.setRate_status_300(0f);
            overall.setRate_status_400(0f);
            overall.setRate_status_500(0f);
        }else{
            overall.setRate_method_delete((float) (overallStatistics.getMethod_delete() / visitors));
            overall.setRate_method_get((float) (overallStatistics.getMethod_get() / visitors));
            overall.setRate_method_post((float) (overallStatistics.getMethod_post() / visitors));
            overall.setRate_method_put((float) (overallStatistics.getMethod_put() / visitors));
            overall.setRate_status_100((float) (overallStatistics.getStatus_100() / visitors));
            overall.setRate_status_200((float) (overallStatistics.getStatus_200() / visitors));
            overall.setRate_status_300((float) (overallStatistics.getStatus_300() / visitors));
            overall.setRate_status_400((float) (overallStatistics.getStatus_400() / visitors));
            overall.setRate_status_500((float) (overallStatistics.getStatus_500() / visitors));

        }
        overall.setTPS(overallStatistics.getTPS());
        keeptoSave.setOverall(overall);
    }
}
