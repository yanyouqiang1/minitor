package app.webInterface.entity.method;

import app.database.domain.Monitor_method;
import lombok.Data;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Method {
    private String name;

    private long id;

    private Long request_visitors,response_visitors;

    private long status_100,status_200,status_300,status_400,status_500;

    private float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private Integer response_min,response_max,response_avg,response_median,response_90,response_99;

    private long TPS;

    public void generate(Monitor_method monitor_method){
        name = monitor_method.getName();
        id = monitor_method.getId();
        request_visitors = monitor_method.getRequest_visitors();
        response_visitors = monitor_method.getResponse_visitors();
        status_100 = monitor_method.getStatus_100();
        status_200 = monitor_method.getStatus_200();
        status_300 = monitor_method.getStatus_300();
        status_400 = monitor_method.getStatus_400();
        status_500 = monitor_method.getStatus_500();
        rate_status_100 = monitor_method.getRate_status_100();
        rate_status_200 = monitor_method.getRate_status_200();
        rate_status_300 = monitor_method.getRate_status_300();
        rate_status_400 = monitor_method.getRate_status_400();
        rate_status_500 = monitor_method.getRate_status_500();
        response_min = monitor_method.getResponse_min();
        response_max = monitor_method.getResponse_max();
        response_avg = monitor_method.getResponse_avg();
        response_median = monitor_method.getResponse_median();
        response_90 = monitor_method.getResponse_90();
        response_99 = monitor_method.getResponse_99();
        TPS = monitor_method.getTPS();
    }

}
