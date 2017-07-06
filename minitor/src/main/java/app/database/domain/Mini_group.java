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

    private String groupName;

    private String name,popularname;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularname() {
        return popularname;
    }

    public void setPopularname(String popularname) {
        this.popularname = popularname;
    }
}
