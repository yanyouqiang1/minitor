package wang.jeese.springcloud.gateway.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jeese on 2017/5/30.
 */
@Entity
@Table(name = "resource")
public class Resource {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "service_id")
    private Long serviceId;
    @Column(name = "resource_name")
    private String resourceName;
    @Column(name = "description")
    private String description;
    @Column(name = "path")
    private String path;
    @Column(name = "retryable")
    private Boolean retryable;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "order0")
    private Integer order;
    @Column(name = "sensitive_headers")
    private String sensitiveHeaders;
    @Column(name = "is_active")
    private Boolean isActive;
    @Transient
    private Service service;
    @Transient
    private Map<String, Method> methods = new HashMap<>();

    protected Resource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getSensitiveHeaders() {
        return sensitiveHeaders;
    }

    public void setSensitiveHeaders(String sensitiveHeaders) {
        this.sensitiveHeaders = sensitiveHeaders;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Map<String, Method> getMethods() {
        return methods;
    }

    public void setMethods(Map<String, Method> methods) {
        this.methods = methods;
    }
}
