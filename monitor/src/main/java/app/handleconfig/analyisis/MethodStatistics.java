package app.handleconfig.analyisis;

import app.handle.commonHandle.GlobalResource;
import app.database.dao.MethodRepository;
import app.database.domain.Monitor_method;
import app.handle.commonHandle.warehouse.statistics.AbstractMethodStatistics;
import entitylib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
@Scope("prototype")
public class MethodStatistics extends AbstractMethodStatistics {
    protected long status_100, status_200, status_300, status_400, status_500;

    protected float rate_status_100, rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    protected int response_min, response_max, response_avg,response_median,response_90,response_99;

    protected long TPS;

    protected long serviceid, groupid;

    protected LinkedList<Integer> responseTimeList = new LinkedList<>();

    @Override
    public void update(ResponseMessage responseMessage) {
        int responsetime = responseMessage.getResponseTime();
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
                responsetime = Const.MAX_RESPONSE;
                break;
            default:
                break;
        }
        if (response_visitors == 1) {
            response_min = responsetime;
            response_avg = responsetime;
//            serviceid = responseMessage.getServiceId();
//            groupid = responseMessage.getGroupId();

        }
        if (responsetime < response_min) {
            response_min = responsetime;
        }
        if (responsetime > response_max) {
            response_max = responsetime;
        }

        responseTimeList.add(responsetime);
        Collections.sort(responseTimeList);

        response_avg = (int) (((response_visitors - 1) * response_avg + responsetime) / response_visitors);
        TPS = response_visitors / 10;

    }

    @Override
    public void attributeClear() {
        status_100 = 0;
        status_200 = 0;
        status_300 = 0;
        status_400 = 0;
        status_500 = 0;
        rate_status_100 =0;
        rate_status_200 = 0;
        rate_status_300= 0;
        rate_status_400 =0;
        rate_status_500 =0 ;
        response_min = 0;
        response_max = 0;
        response_avg = 0;
        TPS = 0;
        response_median=0;
        response_90=0;
        response_99=0;
        responseTimeList.clear();
    }

    @Autowired
    MethodRepository methodRepository;

    @Override
    public void handleResult(AbstractMethodStatistics method) {
        serviceid = method.getParentResource().getServiceId();
        groupid = method.getParentResource().getParentGroup().getId();
        if (method.getResponse_visitors() != 0) {
            rate_status_100 = (float) status_100 / method.getResponse_visitors();
            rate_status_200 = (float) status_200 / method.getResponse_visitors();
            rate_status_300 = (float) status_300 / method.getResponse_visitors();
            rate_status_400 = (float) status_400 / method.getResponse_visitors();
            rate_status_500 = (float) status_500 / method.getResponse_visitors();
            if(responseTimeList.size()>0) {
                response_median = responseTimeList.get(responseTimeList.size() / 2);
            }
            response_90 = responseTimeList.get((int)(responseTimeList.size()*0.9));
            response_99 = responseTimeList.get((int)(responseTimeList.size()*0.99));
        }
        Monitor_method monitor_method = new Monitor_method();
        monitor_method.setRequest_visitors(method.getRequest_visitors());
        monitor_method.setResponse_visitors(method.getResponse_visitors());
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
        monitor_method.setGroupid(groupid);
        monitor_method.setServiceid(serviceid);
        monitor_method.setName(method.getName());
        monitor_method.setOverall(GlobalResource.getCurrentOverall());
        monitor_method.setId(method.getId());

        monitor_method.setResponse_median(response_median);
        monitor_method.setResponse_90(response_90);
        monitor_method.setResponse_99(response_99);

        methodRepository.save(monitor_method);
    }
}
