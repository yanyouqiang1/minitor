package app.webInterface.entity.overview;

import app.database.domain.Monitor_group;
import app.database.domain.Monitor_overall;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
@Data
public class Overview {
    private long id;

    private Long request_visitors,response_visitors;

    private long status_100,status_200,status_300,status_400,status_500;

    private float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private float successRate;

    private Integer response_min,response_max,response_avg,response_median,response_90,response_99;

    private long TPS;

    private List<Monitor_group> groups;

    public void generate(Monitor_overall overall,List<Monitor_group> monitor_groups){
        id = overall.getId();
        request_visitors = overall.getRequest_visitors();
        response_visitors = overall.getResponse_visitors();
        status_100 = overall.getStatus_100();
        status_200 = overall.getStatus_200();
        status_300 = overall.getStatus_300();
        status_400 = overall.getStatus_400();
        status_500 = overall.getStatus_500();
        rate_status_100 = overall.getRate_status_100();
        rate_status_200 = overall.getRate_status_200();
        rate_status_300 = overall.getRate_status_300();
        rate_status_400 = overall.getRate_status_400();
        rate_status_500 = overall.getRate_status_500();
        successRate = 1-rate_status_500;
        response_min = overall.getResponse_min();
        response_max = overall.getResponse_max();
        response_avg = overall.getResponse_avg();
        response_median = overall.getResponse_median();
        response_90 = overall.getResponse_90();
        response_99 = overall.getResponse_99();
        TPS = overall.getTPS();
        this.groups = monitor_groups;
    }

}
