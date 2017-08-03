package app.webInterface.entity.observation;

import app.database.domain.Monitor_services;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class RestServiceResponse {
    private String name;
    private Long id;
    private List<Integer> responseTime;

    public RestServiceResponse() {
        responseTime = new LinkedList<Integer>();
    }

    public static RestServiceResponse generate(List<Monitor_services> monitor_services) {
        RestServiceResponse serviceResponse = new RestServiceResponse();
        if (monitor_services != null && !monitor_services.isEmpty()) {
            serviceResponse.setName(monitor_services.get(0).getName());
            serviceResponse.setId(monitor_services.get(0).getId());
            for (Monitor_services services : monitor_services) {
                serviceResponse.getResponseTime().add(services.getResponse_avg());
            }
        }
        return serviceResponse;
    }
}
