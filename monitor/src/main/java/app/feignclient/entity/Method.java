package app.feignclient.entity;

import lombok.Data;

/**
 * Created by jeese on 2017/5/30.
 */
@Data
public class Method {

    private Long id;
    private Long resourceId;
    private Long authorityId;
    private String methodName;
    private String description;
    private Boolean authentication;
    private Boolean authorization;
    private Boolean enabled;
    private Boolean isActive;
}
