package app.outerInterface.entity.observation;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Data
public class RestServiceResponse {
    private String name;
    private List<Integer> responseTime;

    public RestServiceResponse() {
        responseTime = new LinkedList<Integer>();
    }

    public static RestServiceResponse generate(String serviceName, int[] reponses) {
        RestServiceResponse serviceResponse = new RestServiceResponse();
        serviceResponse.setName(serviceName);
        for (int i = 0; i < reponses.length; i++) {
            serviceResponse.getResponseTime().add(reponses[i]);
        }
        return serviceResponse;
    }
}
