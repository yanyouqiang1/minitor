package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Administrator on 2017/7/11.
 */
@Entity
public class Minitor_resource {
    Long id;

    Long visitors;

    Long groupId;

    String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Minitor_overall overall;

    public Minitor_resource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Minitor_overall getOverall() {
        return overall;
    }

    public void setOverall(Minitor_overall overall) {
        this.overall = overall;
    }
}
