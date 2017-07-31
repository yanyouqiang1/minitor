package app.feignclient.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeese on 2017/5/30.
 */
@Data
public class Resource {

    private Long id;
    private Long groupId;
    private Long serviceId;
    private String resourceName;
    private String description;
    private String path;
    private Boolean retryable;
    private Boolean enabled;
    private Integer order;
    private String sensitiveHeaders;
    private Boolean isActive;
    private Service service;
    private List<Method> methods = new ArrayList<Method>();

}
