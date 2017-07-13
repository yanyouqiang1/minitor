package app.webInterface.entity.method;

import app.database.domain.Monitor_method;

/**
 * Created by Administrator on 2017/7/10.
 */
public class Method {
    private String name;

    private long id;

    private long visitors;

    private long status_100,status_200,status_300,status_400,status_500;

    private float rate_status_100,rate_status_200, rate_status_300, rate_status_400, rate_status_500;

    private int response_min,response_max,resposne_avg;

    private long TPS;

    public void generate(Monitor_method monitor_method){
        name = monitor_method.getName();
        id = monitor_method.getId();
        visitors = monitor_method.getVisitors();
        status_100 = monitor_method.getStatus_100();
        status_200 = monitor_method.getStatus_200();
        status_300 = monitor_method.getStatus_300();
        status_400 = monitor_method.getStatus_400();
        status_500 = monitor_method.getStatus_500();
        rate_status_100 = monitor_method.getRate_status_100();
        rate_status_200 = monitor_method.getRate_status_200();
        rate_status_300 = monitor_method.getRate_status_300();
        rate_status_400 = monitor_method.getRate_status_400();
        rate_status_500 = monitor_method.getRate_status_500();
        response_min = monitor_method.getResponse_min();
        response_max = monitor_method.getResponse_max();
        resposne_avg = monitor_method.getResponse_avg();
        TPS = monitor_method.getTPS();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVisitors() {
        return visitors;
    }

    public void setVisitors(long visitors) {
        this.visitors = visitors;
    }

    public long getStatus_100() {
        return status_100;
    }

    public void setStatus_100(long status_100) {
        this.status_100 = status_100;
    }

    public long getStatus_200() {
        return status_200;
    }

    public void setStatus_200(long status_200) {
        this.status_200 = status_200;
    }

    public long getStatus_300() {
        return status_300;
    }

    public void setStatus_300(long status_300) {
        this.status_300 = status_300;
    }

    public long getStatus_400() {
        return status_400;
    }

    public void setStatus_400(long status_400) {
        this.status_400 = status_400;
    }

    public long getStatus_500() {
        return status_500;
    }

    public void setStatus_500(long status_500) {
        this.status_500 = status_500;
    }

    public float getRate_status_100() {
        return rate_status_100;
    }

    public void setRate_status_100(float rate_status_100) {
        this.rate_status_100 = rate_status_100;
    }

    public float getRate_status_200() {
        return rate_status_200;
    }

    public void setRate_status_200(float rate_status_200) {
        this.rate_status_200 = rate_status_200;
    }

    public float getRate_status_300() {
        return rate_status_300;
    }

    public void setRate_status_300(float rate_status_300) {
        this.rate_status_300 = rate_status_300;
    }

    public float getRate_status_400() {
        return rate_status_400;
    }

    public void setRate_status_400(float rate_status_400) {
        this.rate_status_400 = rate_status_400;
    }

    public float getRate_status_500() {
        return rate_status_500;
    }

    public void setRate_status_500(float rate_status_500) {
        this.rate_status_500 = rate_status_500;
    }

    public int getResponse_min() {
        return response_min;
    }

    public void setResponse_min(int response_min) {
        this.response_min = response_min;
    }

    public int getResponse_max() {
        return response_max;
    }

    public void setResponse_max(int response_max) {
        this.response_max = response_max;
    }

    public int getResposne_avg() {
        return resposne_avg;
    }

    public void setResposne_avg(int resposne_avg) {
        this.resposne_avg = resposne_avg;
    }

    public long getTPS() {
        return TPS;
    }

    public void setTPS(long TPS) {
        this.TPS = TPS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
