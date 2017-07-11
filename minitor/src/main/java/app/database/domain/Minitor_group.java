package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/6.
 */
@Entity
public class Minitor_group {
    private Long id;

    private Long visitors;

    private Float rate;

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Minitor_overall overall;

    public Minitor_group() {
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

    public Minitor_overall getOverall() {
        return overall;
    }

    public void setOverall(Minitor_overall overall) {
        this.overall = overall;
    }
}
