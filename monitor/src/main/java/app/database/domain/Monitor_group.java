package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by Administrator on 2017/7/6.
 */
@Entity
public class Monitor_group {
    private Long id;

    private Long visitors;

    private Float rate;

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Monitor_overall overall;

    public Monitor_group() {
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

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Monitor_overall getOverall() {
        return overall;
    }

    public void setOverall(Monitor_overall overall) {
        this.overall = overall;
    }
}
