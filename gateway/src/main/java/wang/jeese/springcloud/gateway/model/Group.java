package wang.jeese.springcloud.gateway.model;

import javax.persistence.*;

/**
 * Created by jeese on 2017/6/23.
 */
@Entity
@Table(name = "group0")
public class Group {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "description")
    private String description;
    @Column(name = "is_active")
    private Boolean isActive;

    public Group() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
