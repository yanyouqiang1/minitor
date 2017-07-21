package app.schedule.entity;

import app.database.domain.Monitor_method;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
public class Method {
    private Long id;

    private String name;

    private Long serviceid;

    private Long visitors;

    private Float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private Integer response_min, response_max, response_avg;

    private Long TPS;


    public void generate(Monitor_method method){
        this.id = method.getId();
        this.serviceid = method.getServiceid();
        this.name = method.getName();
        this.visitors = method.getVisitors();
        this.rate_status_100= method.getRate_status_100();
        this.rate_status_200 = method.getRate_status_200();
        this.rate_status_300 = method.getRate_status_300();
        this.rate_status_400 = method.getRate_status_400();
        this.rate_status_500 = method.getRate_status_500();
        this.response_avg = method.getResponse_avg();
        this.response_min = method.getResponse_min();
        this.response_max = method.getResponse_max();
        this.TPS = method.getTPS();
    }
}
