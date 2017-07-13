package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/11.
 */
@Entity
public class Monitor_services {
    @Id
    @GeneratedValue
    private Long columnid;

    Long id;
    String name;
    Long visitors;

    private Integer response_min,response_max,response_avg;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Monitor_overall overall;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public Monitor_overall getOverall() {
        return overall;
    }

    public void setOverall(Monitor_overall overall) {
        this.overall = overall;
    }

    public Long getColumnid() {
        return columnid;
    }

    public void setColumnid(Long columnid) {
        this.columnid = columnid;
    }

    public Integer getResponse_min() {
        return response_min;
    }

    public void setResponse_min(Integer response_min) {
        this.response_min = response_min;
    }

    public Integer getResponse_max() {
        return response_max;
    }

    public void setResponse_max(Integer response_max) {
        this.response_max = response_max;
    }

    public Integer getResponse_avg() {
        return response_avg;
    }

    public void setResponse_avg(Integer response_avg) {
        this.response_avg = response_avg;
    }
}
