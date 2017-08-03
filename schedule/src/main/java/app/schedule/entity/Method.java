package app.schedule.entity;

import app.database.domain.Monitor_method;
import lombok.Data;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Data
public class Method {
    private Long id;

    private String name;

    private Long serviceid;

    private Long visitors;

    private Float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private Integer response_min, response_max, response_avg;

    private Long TPS;


    public static Method generate(Monitor_method method){
        Method method1 = new Method();
        method1.id = method.getId();
        method1.serviceid = method.getServiceid();
        method1.name = method.getName();
        method1.visitors = method.getResponse_visitors();
        method1.rate_status_100= method.getRate_status_100();
        method1.rate_status_200 = method.getRate_status_200();
        method1.rate_status_300 = method.getRate_status_300();
        method1.rate_status_400 = method.getRate_status_400();
        method1.rate_status_500 = method.getRate_status_500();
        method1.response_avg = method.getResponse_avg();
        method1.response_min = method.getResponse_min();
        method1.response_max = method.getResponse_max();
        method1.TPS = method.getTPS();
        return method1;
    }
}
