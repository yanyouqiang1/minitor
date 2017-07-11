package app.handleconfig.analyisis;

import app.Global;
import app.database.dao.OverallRepository;
import app.database.domain.Minitor_overall;
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

    public long status_100,status_200,status_300,status_400,status_500;

    public int response_min,response_max,resposne_avg;

    public long TPS;

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
        Minitor_overall minitor_overall = new Minitor_overall();
        minitor_overall.setVisitors(overall.getVisitors());
        minitor_overall.setResponse_max(response_min);
        minitor_overall.setResponse_min(response_max);
        minitor_overall.setResposne_avg(resposne_avg);
        minitor_overall.setStatus_100(status_100);
        minitor_overall.setStatus_200(status_200);
        minitor_overall.setStatus_300(status_300);
        minitor_overall.setStatus_400(status_400);
        minitor_overall.setStatus_500(status_500);
        minitor_overall.setTPS(TPS);
        Minitor_overall current = overallRepository.saveAndFlush(minitor_overall);
        Global.CurrentOverallId = current.getId();
    }

}
