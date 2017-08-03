package app.schedule.entity;

import app.database.domain.Monitor_services;
import app.schedule.rancherImpl.RancherService;
import lombok.Data;

/**
 * Created by Administrator on 2017/7/21 0021.
 */
@Data
public class Service {
    private String name;
    private Long id;
    private Long visitors;
    private Integer response_min,response_max,response_avg;

    public void generate(Monitor_services services){
        this.name = services.getName();
        this.visitors = services.getResponse_visitors();
        this.id= services.getId();
        this.response_avg = services.getResponse_avg();
        this.response_max = services.getResponse_max();
        this.response_min = services.getResponse_min();
    }

}
