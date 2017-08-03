package app.webInterface.entity.observation;

import app.database.domain.Monitor_services;
import app.database.domain.Schedule_service;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class RestServiceContainer {
    private String name;
    private List<Integer>  scales;

    public RestServiceContainer() {
        scales = new LinkedList<Integer>();
    }

    public static RestServiceContainer generate(List<Schedule_service> schedule_services){
        RestServiceContainer serviceContainer = new RestServiceContainer();
        if(schedule_services!=null){
            for(Schedule_service services:schedule_services){
                serviceContainer.getScales().add(services.getScale());
            }
        }
        return serviceContainer;
    }

}
