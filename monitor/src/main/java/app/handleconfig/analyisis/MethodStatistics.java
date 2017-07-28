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
    protected long status_100, status_200, status_300, status_400, status_500;

    protected float rate_status_100, rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    protected int response_min, response_max, response_avg;

    protected long TPS;

    protected long serviceid,groupid;
    @Override
    public void update(MsgEntity msgEntity) {
        switch (msgEntity.getHttpStatus()) {
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
        int responsetime = msgEntity.getResponseTime();
        if(visitors==1){
            response_min = responsetime;
            serviceid = msgEntity.getServiceId();
            groupid = msgEntity.getGroupId();
        }
        if (responsetime < response_min) {
            response_min = responsetime;
        }
        if (responsetime > response_max) {
            response_max = responsetime;
        }
        response_avg = (int) ((visitors - 1) * response_avg / visitors);

        TPS = visitors / 10;

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
        TPS = 0;
    }

    @Autowired
    MethodRepository methodRepository;

    @Override
    public void handleResult(AbstractMethodStatistics method) {
        if(method.getVisitors()!=0) {
            rate_status_100 = (float) status_100 / method.getVisitors();
            rate_status_200 = (float) status_200 / method.getVisitors();
            rate_status_300 = (float) status_300 / method.getVisitors();
            rate_status_400 = (float) status_400 / method.getVisitors();
            rate_status_500 = (float) status_500 / method.getVisitors();
        }
        Monitor_method monitor_method = new Monitor_method();
        monitor_method.setVisitors(method.getVisitors());
        monitor_method.setResponse_max(response_max);
        monitor_method.setResponse_min(response_min);
        monitor_method.setResponse_avg(response_avg);
        monitor_method.setStatus_100(status_100);
        monitor_method.setStatus_200(status_200);
        monitor_method.setStatus_300(status_300);
        monitor_method.setStatus_400(status_400);
        monitor_method.setStatus_500(status_500);
        monitor_method.setRate_status_100(rate_status_100);
        monitor_method.setRate_status_200(rate_status_200);
        monitor_method.setRate_status_300(rate_status_300);
        monitor_method.setRate_status_400(rate_status_400);
        monitor_method.setRate_status_500(rate_status_500);
        monitor_method.setTPS(TPS);
        monitor_method.setResourceId(method.getParentResource().getId());
        monitor_method.setGroupid(method.getParentResource().getParentGroup().getId());
        monitor_method.setServiceid(Global.getServiceIdBymethodId(method.getId()));
        monitor_method.setName(method.getName());
        monitor_method.setOverall(Global.getCurrentOverall());
        monitor_method.setId(method.getId());
        methodRepository.save(monitor_method);
    }
}
