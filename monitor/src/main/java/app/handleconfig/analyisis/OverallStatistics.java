package app.handleconfig.analyisis;

import app.handle.commonHandle.GlobalResource;
import app.database.dao.OverallRepository;
import app.database.domain.Monitor_overall;
import app.handle.commonHandle.warehouse.statistics.AbstractOverallStatistics;
import entitylib.ResponseMessage;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
@Scope("prototype")
public class OverallStatistics extends AbstractOverallStatistics {

    protected long status_100, status_200, status_300, status_400, status_500;

    protected float rate_status_100, rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    protected int response_min, response_max, response_avg,response_median,response_90,response_99;

    protected long TPS;

    protected LinkedList<Integer> responseTimeList = new LinkedList<>();
    @Override
    public void update(ResponseMessage responseMessage) {
        switch (responseMessage.getHttpStatus()) {
            case 100:
                status_100++;
                break;
            case 200:
                status_200++;
                break;
            case 300:
                status_300++;
                break;
            case 400:
                status_400++;
                break;
            case 500:
                status_500++;
                break;
        }

        int responsetime = responseMessage.getResponseTime();
        if (response_visitor == 1) {
            response_min = responsetime;
        }
        if (responsetime < response_min) {
            response_min = responsetime;
        }
        if (responsetime > response_max) {
            response_max = responsetime;
        }
        response_avg = (int) (((response_visitor - 1) * response_avg + responsetime) / response_visitor);

        responseTimeList.add(responsetime);
        Collections.sort(responseTimeList);

        TPS = response_visitor / 10;
    }
    @Override
    public void attributeClear() {
        status_100 = 0;
        status_200 = 0;
        status_300 = 0;
        status_400 = 0;
        status_500 = 0;
        response_min = 0;
        response_max = 0;
        response_avg = 0;
        response_median = 0;
        TPS = 0;
        responseTimeList.clear();
    }

    @Autowired
    OverallRepository overallRepository;

    @Override
    public void handleResult(AbstractOverallStatistics overall) {
        if (overall.getResponse_visitor() != 0) {
            rate_status_100 = (float) status_100 / overall.getResponse_visitor();
            rate_status_200 = (float) status_200 / overall.getResponse_visitor();
            rate_status_300 = (float) status_300 / overall.getResponse_visitor();
            rate_status_400 = (float) status_400 / overall.getResponse_visitor();
            rate_status_500 = (float) status_500 / overall.getResponse_visitor();

            response_median =responseTimeList.get(responseTimeList.size()/2);
            response_90 = responseTimeList.get((int)(responseTimeList.size()*0.9));
            response_99 = responseTimeList.get((int)(responseTimeList.size()*0.99));
        }



        Monitor_overall monitor_overall = new Monitor_overall();
        monitor_overall.setRequest_visitors(overall.getRequest_visitor());
        monitor_overall.setResponse_visitors(overall.getResponse_visitor());
        monitor_overall.setResponse_max(response_max);
        monitor_overall.setResponse_min(response_min);
        monitor_overall.setResponse_avg(response_avg);
        monitor_overall.setStatus_100(status_100);
        monitor_overall.setStatus_200(status_200);
        monitor_overall.setStatus_300(status_300);
        monitor_overall.setStatus_400(status_400);
        monitor_overall.setStatus_500(status_500);
        monitor_overall.setRate_status_100(rate_status_100);
        monitor_overall.setRate_status_200(rate_status_200);
        monitor_overall.setRate_status_300(rate_status_300);
        monitor_overall.setRate_status_400(rate_status_400);
        monitor_overall.setRate_status_500(rate_status_500);
        monitor_overall.setTPS(TPS);
        monitor_overall.setResponse_median(response_median);
        monitor_overall.setResponse_90(response_90);
        monitor_overall.setResponse_99(response_99);
        GlobalResource.setCurrentOverall(overallRepository.saveAndFlush(monitor_overall));
    }

}
