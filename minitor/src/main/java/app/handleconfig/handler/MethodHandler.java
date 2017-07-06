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
        method.setStatus_200(methodStatistics.getStatus_200());
        method.setStatus_300(methodStatistics.getStatus_300());
        method.setStatus_400(methodStatistics.getStatus_400());
        method.setStatus_500(methodStatistics.getStatus_500());
        method.setTPS(methodStatistics.getTPS());
        method.setResource(methodStatistics.getResource());
        method.setGroupname(methodStatistics.getGroupname());
        keeptoSave.save(method);
    }
}
