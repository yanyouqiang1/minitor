package app.handleconfig.analyisis;

import app.Global;
import app.database.dao.MethodRepository;
import app.database.domain.Monitor_method;
import app.handle.commonHandle.warehouse.statistics.AbstractMethodStatistics;
import entitylib.MsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
@Scope("prototype")
public class MethodStatistics extends AbstractMethodStatistics {
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

    @Autowired
    MethodRepository methodRepository;
    @Override
    public void handleResult(AbstractMethodStatistics method) {
        Monitor_method monitor_method = new Monitor_method();
        monitor_method.setVisitors(method.getVisitors());
        monitor_method.setResponse_max(response_min);
        monitor_method.setResponse_min(response_max);
        monitor_method.setResposne_avg(resposne_avg);
        monitor_method.setStatus_100(status_100);
        monitor_method.setStatus_200(status_200);
        monitor_method.setStatus_300(status_300);
        monitor_method.setStatus_400(status_400);
        monitor_method.setStatus_500(status_500);
        monitor_method.setTPS(TPS);
        monitor_method.setResourceId(method.getParentResource().getId());
        monitor_method.setName(method.getName());
        monitor_method.setOverall(Global.CurrentOverall);
        methodRepository.save(monitor_method);
    }
}
