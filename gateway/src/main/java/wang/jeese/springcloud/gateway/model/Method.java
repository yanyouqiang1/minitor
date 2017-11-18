package wang.jeese.springcloud.gateway.model;

import javax.persistence.*;

/**
 * Created by jeese on 2017/5/30.
 */
@Entity
@Table(name = "method")
public class Method {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "resource_id")
    private Long resourceId;
    @Column(name = "authority_id")
    private Long authorityId;
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "description")
    private String description;
    @Column(name = "authentication")
    private Boolean authentication;
    @Column(name = "authorization")
    private Boolean authorization;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "is_active")
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public Boolean getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Boolean authorization) {
        this.authorization = authorization;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
