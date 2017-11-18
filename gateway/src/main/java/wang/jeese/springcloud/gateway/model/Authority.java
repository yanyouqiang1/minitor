package wang.jeese.springcloud.gateway.model;

import javax.persistence.*;

/**
 * Created by jeese on 2017/6/2.
 */
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authority_name")
    private String authorityName;
    @Column(name = "description")
    private String description;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "is_active")
    private Boolean isActive;

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
