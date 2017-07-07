package app.database.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/7.
 */
public class Basegroup {
    private Long id;

    protected Long visitors;

    protected Float rate;

    protected String groupName;

    protected String name,popularname;

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

    public void generate(Mini_group m){
        this.id = m.getId();
        this.groupName = m.getGroupName();
        this.name = m.getName();
        this.popularname = m.getPopularname();
        this.rate = m.getRate();
        this.visitors = m.getVisitors();
    }
}
