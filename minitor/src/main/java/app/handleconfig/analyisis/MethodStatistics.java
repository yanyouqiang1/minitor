package app.handleconfig.analyisis;

import app.database.domain.Minitor_method;
import app.database.domain.Minitor_overall;
import app.handle.commonHandle.warehouse.statistics.AbstractMethodStatistics;
import entitylib.MsgEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
@Scope("prototype")
public class MethodStatistics extends AbstractMethodStatistics {
    protected long method_get,method_post,method_put,method_delete;

    protected long status_100,status_200,status_300,status_400,status_500;

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

    @Override
    public void handleResult(AbstractMethodStatistics method) {
        Minitor_method minitor_method = new Minitor_method();
        minitor_method.setVisitors(method.getVisitors());
        minitor_method.setResponse_max(response_min);
        minitor_method.setResponse_min(response_max);
        minitor_method.setResposne_avg(resposne_avg);
        minitor_method.setStatus_100(status_100);
        minitor_method.setStatus_200(status_200);
        minitor_method.setStatus_300(status_300);
        minitor_method.setStatus_400(status_400);
        minitor_method.setStatus_500(status_500);
        minitor_method.setTPS(TPS);
        minitor_method.setResourceId(method.getParentResource().getResourceid());

    }
}
