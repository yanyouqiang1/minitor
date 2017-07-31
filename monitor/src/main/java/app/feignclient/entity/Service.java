package app.feignclient.entity;

import lombok.Data;

/**
 * Created by jeese on 2017/5/30.
 */
@Data
public class Service {

    private Long id;
    private String serviceName;
    private String description;
    private String serviceId;
    private String url;
    private Boolean isActive;
    protected Service() {
    }
}
