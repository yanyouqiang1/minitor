package app.webInterface.entity.overview;

import app.database.domain.Monitor_group;
import app.database.domain.Monitor_overall;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Overview {
    private long id;

    private long visitors;

    private long status_100,status_200,status_300,status_400,status_500;

    private float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private float successRate;

    private int response_min,response_max,resposne_avg;

    private long TPS;

    private List<Monitor_group> groups;

    public void generate(Monitor_overall overall,List<Monitor_group> monitor_groups){
        id = overall.getId();
        visitors = overall.getVisitors();
        status_100 = overall.getStatus_100();
        status_200 = overall.getStatus_200();
        status_300 = overall.getStatus_300();
        status_400 = overall.getStatus_400();
        status_500 = overall.getStatus_500();
        rate_status_100 = overall.getRate_status_100();
        rate_status_200 = overall.getRate_status_200();
        rate_status_300 = overall.getRate_status_300();
        rate_status_400 = overall.getRate_status_400();
        rate_status_500 = overall.getRate_status_500();
        successRate = 1-rate_status_500;
        response_min = overall.getResponse_min();
        response_max = overall.getResponse_max();
        resposne_avg = overall.getResponse_avg();
        TPS = overall.getTPS();
        this.groups = monitor_groups;
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

    public float getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(float successRate) {
        this.successRate = successRate;
    }

    public List<Monitor_group> getGroups() {
        return groups;
    }

    public void setGroups(List<Monitor_group> groups) {
        this.groups = groups;
    }
}
