package app.database.domain;

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
public class Mini_group {
    @Id
    @GeneratedValue
    private Long id;

    private Long visitors;

    private Float rate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Mini_overall overall;

    @OneToMany(mappedBy = "group")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Cascade(value = {CascadeType.ALL})
    private Set<Mini_method> methods = new HashSet<Mini_method>();

    public Mini_group() {
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

    public Mini_overall getOverall() {
        return overall;
    }

    public void setOverall(Mini_overall overall) {
        this.overall = overall;
    }

    public Set<Mini_method> getMethods() {
        return methods;
    }

    public void setMethods(Set<Mini_method> methods) {
        this.methods = methods;
    }
}
