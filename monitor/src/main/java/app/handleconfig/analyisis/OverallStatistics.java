package app.handleconfig.analyisis;

import app.Global;
import app.database.dao.OverallRepository;
import app.database.domain.Monitor_overall;
import app.handle.commonHandle.warehouse.statistics.AbstractOverallStatistics;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
@Scope("prototype")
public class OverallStatistics extends AbstractOverallStatistics{

    protected long status_100,status_200,status_300,status_400,status_500;

    protected Float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    protected int response_min,response_max,resposne_avg;

    protected long TPS;

    @Override
    public void update(MsgEntity msgEntity) {
        switch (msgEntity.getHttpStatus()){
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

        int responsetime = msgEntity.getResposneTime();
        if(responsetime<response_min){
            response_min = responsetime;
        }
        if(responsetime>response_max){
            response_max = responsetime;
        }

        resposne_avg = (int) ((visitors-1)*resposne_avg/visitors);

        TPS = visitors/10;
    }

    @Override
    public void attributeClear() {
        status_100 = 0;
        status_200 = 0;
        status_300 = 0;
        status_400 = 0;
        status_500 = 0;
        response_min=0;
        response_max=0;
        resposne_avg=0;
        TPS =0;
    }

    @Autowired
    OverallRepository overallRepository;
    @Override
    public void handleResult(AbstractOverallStatistics overall) {
        rate_status_100 = (float)status_100/visitors;
        rate_status_200 = (float)status_200/visitors;
        rate_status_300 = (float)status_300/visitors;
        rate_status_400 = (float)status_400/visitors;
        rate_status_500 = (float)status_500/visitors;

        Monitor_overall monitor_overall = new Monitor_overall();
        monitor_overall.setVisitors(overall.getVisitors());
        monitor_overall.setResponse_max(response_min);
        monitor_overall.setResponse_min(response_max);
        monitor_overall.setResposne_avg(resposne_avg);
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
        Global.CurrentOverall = overallRepository.saveAndFlush(monitor_overall);
    }

}
