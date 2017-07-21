package app.schedule.entity;

import app.database.domain.Monitor_services;
import lombok.Data;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Data
public class Service {
    Long id;
    String name;
    Long visitors;
    Integer response_min,response_max,response_avg;

    public void generate(Monitor_services services){
        this.id = services.getId();
        this.name = services.getName();
        this.visitors = services.getVisitors();
        this.response_avg = services.getResponse_avg();
        this.response_max = services.getResponse_max();
        this.response_min = services.getResponse_min();
    }
}
