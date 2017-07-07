package app.handleconfig.handler;

import app.database.domain.Mini_method;
import app.database.domain.Mini_method;
import app.database.service.KeeptoSave;
import app.handle.commonHandle.warehouse.AbstractMethodStatistics;
import app.handle.commonHandle.warehouse.MethodStatisticsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/6.
 */
@Component
public class MethodHandler implements MethodStatisticsHandler {
    @Autowired
    KeeptoSave keeptoSave;
    @Override
    public void handle(AbstractMethodStatistics methodStatistics) {
        Mini_method method = new Mini_method();
        method.setVisitors(methodStatistics.getVisitors());
        method.setMethod_delete(methodStatistics.getMethod_delete());
        method.setMethod_get(methodStatistics.getMethod_get());
        method.setMethod_post(methodStatistics.getMethod_post());
        method.setMethod_put(methodStatistics.getMethod_put());
        method.setResponse_max(methodStatistics.getResponse_max());
        method.setResponse_min(methodStatistics.getResponse_min());
        method.setResposne_avg(methodStatistics.getResposne_avg());
        method.setStatus_100(methodStatistics.getStatus_100());
        method.setStatus_200(methodStatistics.getStatus_200());
        method.setStatus_300(methodStatistics.getStatus_300());
        method.setStatus_400(methodStatistics.getStatus_400());
        method.setStatus_500(methodStatistics.getStatus_500());
        method.setTPS(methodStatistics.getTPS());
        method.setResource(methodStatistics.getResource());
        method.setGroupname(methodStatistics.getGroupname());

        //rate
        long visitors = methodStatistics.getVisitors();
        method.setRate_method_delete((float) (methodStatistics.getMethod_delete()/visitors));
        method.setRate_method_get((float) (methodStatistics.getMethod_get()/visitors));
        method.setRate_method_post((float) (methodStatistics.getMethod_post()/visitors));
        method.setRate_method_put((float) (methodStatistics.getMethod_put()/visitors));
        method.setRate_status_100((float) (methodStatistics.getStatus_100()/visitors));
        method.setRate_status_200((float) (methodStatistics.getStatus_200()/visitors));
        method.setRate_status_300((float) (methodStatistics.getStatus_300()/visitors));
        method.setRate_status_400((float) (methodStatistics.getStatus_400()/visitors));
        method.setRate_status_500((float) (methodStatistics.getStatus_500()/visitors));

        keeptoSave.save(method);
    }
}
