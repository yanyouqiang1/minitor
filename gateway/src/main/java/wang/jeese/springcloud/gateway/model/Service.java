package wang.jeese.springcloud.gateway.model;

import javax.persistence.*;

/**
 * Created by jeese on 2017/5/30.
 */
@Entity
@Table(name = "service")
public class Service {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "service_name")
    private String serviceName;
    @Column(name = "description")
    private String description;
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "url")
    private String url;
    @Column(name = "is_active")
    private Boolean isActive;

    protected Service() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
