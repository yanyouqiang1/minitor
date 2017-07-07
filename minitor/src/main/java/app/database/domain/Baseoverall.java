package app.database.domain;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/7/7.
 */
public class Baseoverall {
    private Long id;

    protected Long visitors;

    protected Long method_get,method_post,method_put,method_delete;

    protected Long status_100,status_200,status_300,status_400,status_500;

    protected Float rate_method_get,rate_method_post,rate_method_put,rate_method_delete;

    protected Float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;


    private Integer response_min,response_max,resposne_avg;

    private Long TPS;

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

    public Long getMethod_get() {
        return method_get;
    }

    public void setMethod_get(Long method_get) {
        this.method_get = method_get;
    }

    public Long getMethod_post() {
        return method_post;
    }

    public void setMethod_post(Long method_post) {
        this.method_post = method_post;
    }

    public Long getMethod_put() {
        return method_put;
    }

    public void setMethod_put(Long method_put) {
        this.method_put = method_put;
    }

    public Long getMethod_delete() {
        return method_delete;
    }

    public void setMethod_delete(Long method_delete) {
        this.method_delete = method_delete;
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

    public Float getRate_method_get() {
        return rate_method_get;
    }

    public void setRate_method_get(Float rate_method_get) {
        this.rate_method_get = rate_method_get;
    }

    public Float getRate_method_post() {
        return rate_method_post;
    }

    public void setRate_method_post(Float rate_method_post) {
        this.rate_method_post = rate_method_post;
    }

    public Float getRate_method_put() {
        return rate_method_put;
    }

    public void setRate_method_put(Float rate_method_put) {
        this.rate_method_put = rate_method_put;
    }

    public Float getRate_method_delete() {
        return rate_method_delete;
    }

    public void setRate_method_delete(Float rate_method_delete) {
        this.rate_method_delete = rate_method_delete;
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
    public void generate(Mini_overall m){
        this.id = m.getId();
        this.TPS = m.getTPS();
        this.method_delete = m.getMethod_delete();
        this.method_get = m.getMethod_get();
        this.method_post = m.getMethod_post();
        this.method_put=m.getMethod_put();
        this.rate_method_delete = m.getRate_method_delete();
        this.rate_method_get = m.getRate_method_get();
        this.rate_method_post = m.getRate_method_post();
        this.rate_method_put = m.getRate_method_put();
        this.rate_status_100 = m.getRate_status_100();
        this.rate_status_200 = m.getRate_status_200();
        this.rate_status_300 = m.getRate_status_300();
        this.rate_status_400 = m.getRate_status_400();
        this.rate_status_500 = m.getRate_status_500();
        this.response_max = m.getResponse_max();
        this.response_min = m.getResponse_min();
        this.resposne_avg = m.getResposne_avg();
        this.status_100 = m.getStatus_100();
        this.status_200 = m.getStatus_200();
        this.status_300 = m.getStatus_300();
        this.status_400 = m.getStatus_400();
        this.status_500 = m.getStatus_500();
        this.visitors = m.getVisitors();
    }
}
