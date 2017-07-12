package app.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/6.
 */
@Entity
public class Monitor_method {
    @Id
    @GeneratedValue
    private Long columnid;

    private Long id;

    @Column(name = "resourceId")
    private Long resourceId;

    private Long visitors;

    private Long status_100,status_200,status_300,status_400,status_500;

    private Float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private Integer response_min, response_max, resposne_avg;

    private Long TPS;

    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Monitor_overall overall;

    public Monitor_method() {
    }

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

    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public Long getStatus_100() {
        return status_100;
    }

    public void setStatus_100(Long status_100) {
        this.status_100 = status_100;
    }

    public Long getStatus_200() {
        return status_200;
    }

    public void setStatus_200(Long status_200) {
        this.status_200 = status_200;
    }

    public Long getStatus_300() {
        return status_300;
    }

    public void setStatus_300(Long status_300) {
        this.status_300 = status_300;
    }

    public Long getStatus_400() {
        return status_400;
    }

    public void setStatus_400(Long status_400) {
        this.status_400 = status_400;
    }

    public Long getStatus_500() {
        return status_500;
    }

    public void setStatus_500(Long status_500) {
        this.status_500 = status_500;
    }

    public Float getRate_status_100() {
        return rate_status_100;
    }

    public void setRate_status_100(Float rate_status_100) {
        this.rate_status_100 = rate_status_100;
    }

    public Float getRate_status_200() {
        return rate_status_200;
    }

    public void setRate_status_200(Float rate_status_200) {
        this.rate_status_200 = rate_status_200;
    }

    public Float getRate_status_300() {
        return rate_status_300;
    }

    public void setRate_status_300(Float rate_status_300) {
        this.rate_status_300 = rate_status_300;
    }

    public Float getRate_status_400() {
        return rate_status_400;
    }

    public void setRate_status_400(Float rate_status_400) {
        this.rate_status_400 = rate_status_400;
    }

    public Float getRate_status_500() {
        return rate_status_500;
    }

    public void setRate_status_500(Float rate_status_500) {
        this.rate_status_500 = rate_status_500;
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

    public Integer getResposne_avg() {
        return resposne_avg;
    }

    public void setResposne_avg(Integer resposne_avg) {
        this.resposne_avg = resposne_avg;
    }

    public Long getTPS() {
        return TPS;
    }

    public void setTPS(Long TPS) {
        this.TPS = TPS;
    }

    public Monitor_overall getOverall() {
        return overall;
    }

    public void setOverall(Monitor_overall overall) {
        this.overall = overall;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getColumnid() {
        return columnid;
    }

    public void setColumnid(Long columnid) {
        this.columnid = columnid;
    }
}
